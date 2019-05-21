package me.syrym

import me.syrym.bot.BotSingleton
import me.syrym.di.servicesModule
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(servicesModule)
    }

    BotSingleton.bot.startPolling()
}
