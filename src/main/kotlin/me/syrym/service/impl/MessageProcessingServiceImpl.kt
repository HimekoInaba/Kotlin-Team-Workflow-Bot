package me.syrym.service.impl

import me.ivmg.telegram.entities.Update
import me.syrym.bot.BotSingleton
import me.syrym.configuration.config
import me.syrym.configuration.errMessage
import me.syrym.configuration.messageFormats
import me.syrym.configuration.messageWelcome
import me.syrym.extension.containsDigits
import me.syrym.extension.containsOnlyDigits
import me.syrym.extension.removeAllNonDigit
import me.syrym.service.AnswerSchedulerService
import me.syrym.service.MessageProcessingService
import me.syrym.util.getHours
import me.syrym.util.getMinutes
import me.syrym.util.getSeconds
import me.syrym.util.parseHHMMSSFormatStringToLocalTime
import me.syrym.util.validator.isInHMSNotation
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
            return
        }

        if (messageText.containsDigits()) {
            if (messageText.containsOnlyDigits()) {
                schedulerService.scheduleBotResponse(chatId, messageText.toInt())
            } else {
                if (isInHMSNotation(messageText)) {
                    processHMSFormatMessage(chatId, messageText)
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

    private fun processHMSFormatMessage(chatId: Long, message: String) {
        schedulerService.scheduleBotResponse(
            chatId, LocalTime.of(
                getHours(message), getMinutes(message), getSeconds(message)
            ).toSecondOfDay()
        )
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
