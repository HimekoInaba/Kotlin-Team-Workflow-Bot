package me.syrym.service.impl

import me.ivmg.telegram.entities.Update
import me.syrym.bot.BotSingleton
import me.syrym.configuration.config
import me.syrym.configuration.errMessage
import me.syrym.configuration.messageFormats
import me.syrym.configuration.messageWelcome
import me.syrym.extension.*
import me.syrym.service.AnswerSchedulerService
import me.syrym.service.MessageProcessingService
import me.syrym.util.parseHHMMSSFormatStringToLocalTime
import org.koin.core.KoinComponent
import java.time.LocalTime
import java.time.format.DateTimeParseException

class MessageProcessingServiceImpl(private val schedulerService: AnswerSchedulerService) : MessageProcessingService,
    KoinComponent {
    private val possibleFormats = config[messageFormats]
    private val greetingMessage = config[messageWelcome]
    private val errorMessage = config[errMessage]

    override fun processMessage(update: Update) {
        if (update.message == null) {
            throw IllegalStateException("message is null")
        }

        val messageText = update.message!!.text
        val chatId = update.message!!.chat.id

        if (messageText == null) {
            sendInvalidInputMessage(chatId)
        }

        if (messageText!!.containsDigits()) {
            if (messageText.containsOnlyDigits()) {
                schedulerService.scheduleBotResponse(chatId, messageText.toInt())
            } else {
                if (messageText.isInHMSNotation()) {
                    schedulerService.scheduleBotResponse(
                        chatId, LocalTime.of(
                            messageText.getHours(), messageText.getMinutes(), messageText.getSeconds()
                        ).toSecondOfDay()
                    )
                } else {
                    processHHMMSSFormatMessage(chatId, messageText.removeAllNonDigit())
                }
            }
        } else if (messageText == "/start") {
            BotSingleton.bot.sendMessage(chatId, "$greetingMessage$possibleFormats")
        } else {
            sendInvalidInputMessage(chatId)
        }
    }

    private fun processHHMMSSFormatMessage(chatId: Long, message: String) {
        try {
            val localTime = parseHHMMSSFormatStringToLocalTime(message)
            schedulerService.scheduleBotResponse(chatId, localTime.toSecondOfDay())
        } catch (ex: DateTimeParseException) {
            sendInvalidInputMessage(chatId)
        }
    }

    private fun sendInvalidInputMessage(chatId: Long) =
        BotSingleton.bot.sendMessage(chatId, "$errorMessage\n$possibleFormats")
}
