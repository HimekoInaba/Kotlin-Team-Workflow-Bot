package me.syrym

import me.ivmg.telegram.bot
import me.ivmg.telegram.dispatch
import me.ivmg.telegram.dispatcher.command
import me.ivmg.telegram.dispatcher.text
import me.syrym.configuration.apiToken
import me.syrym.configuration.config
import me.syrym.service.impl.MessageProcessingServiceImpl
import okhttp3.logging.HttpLoggingInterceptor

fun main(args: Array<String>) {
    val service = MessageProcessingServiceImpl()

    val bot = bot {
        logLevel = HttpLoggingInterceptor.Level.BODY
        token = config[apiToken]
        dispatch {
            command("start") { bot, update -> service.greet(bot, update) }
            text { bot, update -> service.processMessage(bot, update) }
        }
    }
    bot.startPolling()
}
