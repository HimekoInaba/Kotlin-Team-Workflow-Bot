package me.syrym.service.impl

import me.ivmg.telegram.Bot
import me.ivmg.telegram.entities.Update
import me.syrym.configuration.config
import me.syrym.configuration.messageFormats
import me.syrym.configuration.messageWelcome
import me.syrym.extension.*
import me.syrym.service.MessageProcessingService
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class MessageProcessingServiceImpl : MessageProcessingService {
    private val possibleFormats = config[messageFormats]
    private val greetingMessage = config[messageWelcome]
    private val schedulerService = AnswerSchedulerServiceImpl()

    override fun greet(bot: Bot, update: Update) {
        bot.sendMessage(chatId = update.message!!.chat.id, text = greetingMessage + possibleFormats)
    }

    override fun processMessage(bot: Bot, update: Update) {
        val messageText = update.message!!.text
        val chatId = update.message!!.chat.id

        if (messageText?.containsDigits() == true) {
            if (messageText.containsOnlyDigits()) {
                schedulerService.scheduleBotResponse(bot, chatId = chatId, seconds = messageText.toInt())
            } else {
                /*if (messageText.isInHMSNotation()) {
                    schedulerService.scheduleBotResponse(
                        bot, chatId = chatId, seconds =
                        messageText.getHours() * 60 * 60 +
                                messageText.getMinutes() * 60 +
                                messageText.getSeconds()
                    )
                }*/
                try {
                    val localTime = LocalTime.parse(
                        messageText.removeAllNonDigit(),
                        DateTimeFormatter.ofPattern("HH:mm:ss")
                    )
                    schedulerService.scheduleBotResponse(bot, chatId = chatId, seconds = localTime.convertToSeconds())
                } catch (ex: DateTimeParseException) {
                    sendInvalidInputMessage(bot, chatId)
                }
            }
        } else if (messageText != "/start"){
            sendInvalidInputMessage(bot, chatId)
        }
    }

    private fun sendInvalidInputMessage(bot: Bot, chatId: Long) {
        bot.sendMessage(
            chatId = chatId,
            text = "Invalid input! Please, enter your message in following formats:\n$possibleFormats"
        )
    }
}