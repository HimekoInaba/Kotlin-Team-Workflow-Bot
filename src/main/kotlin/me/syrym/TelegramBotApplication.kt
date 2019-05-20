package me.syrym

import me.ivmg.telegram.bot
import me.ivmg.telegram.dispatch
import me.ivmg.telegram.dispatcher.command
import me.ivmg.telegram.dispatcher.text
import me.syrym.configuration.apiToken
import me.syrym.configuration.config
import me.syrym.di.servicesModule
import me.syrym.service.MessageProcessingService
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject

class TelegramBotApplication : KoinComponent {
    private val service: MessageProcessingService by inject()

    fun run() {
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
}

fun main(args: Array<String>) {
    startKoin {
        modules(servicesModule)
    }
    TelegramBotApplication().run()
}
