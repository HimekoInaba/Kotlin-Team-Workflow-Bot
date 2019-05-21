package me.syrym.bot

import me.ivmg.telegram.bot
import me.ivmg.telegram.dispatch
import me.ivmg.telegram.dispatcher.text
import me.syrym.configuration.apiToken
import me.syrym.configuration.config
import me.syrym.service.MessageProcessingService
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.KoinComponent
import org.koin.core.inject

object BotSingleton : KoinComponent {
    private val service: MessageProcessingService by inject()

    val bot = bot {
        logLevel = HttpLoggingInterceptor.Level.BASIC
        token = config[apiToken]
        dispatch {
            text { bot, update -> service.processMessage(update) }
        }
    }
}