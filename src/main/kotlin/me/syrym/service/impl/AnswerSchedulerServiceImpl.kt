package me.syrym.service.impl

import me.syrym.bot.BotSingleton
import me.syrym.configuration.config
import me.syrym.configuration.corePoolSize
import me.syrym.service.AnswerSchedulerService
import org.koin.core.KoinComponent
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class AnswerSchedulerServiceImpl : AnswerSchedulerService, KoinComponent {
    private val executor: ScheduledExecutorService = Executors.newScheduledThreadPool(config[corePoolSize])

    override fun scheduleBotResponse(chatId: Long, seconds: Int) {
        executor.schedule(
            { BotSingleton.bot.sendMessage(chatId, "Answering after $seconds seconds delay") },
            seconds.toLong(),
            TimeUnit.SECONDS
        )
    }
}