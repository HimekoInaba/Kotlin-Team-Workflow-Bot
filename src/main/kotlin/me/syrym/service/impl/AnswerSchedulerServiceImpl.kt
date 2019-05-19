package me.syrym.service.impl

import me.ivmg.telegram.Bot
import me.syrym.configuration.config
import me.syrym.configuration.corePoolSize
import me.syrym.service.AnswerSchedulerService
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class AnswerSchedulerServiceImpl : AnswerSchedulerService {
    private val executor: ScheduledExecutorService = Executors.newScheduledThreadPool(config[corePoolSize])

    override fun scheduleBotResponse(bot: Bot, chatId: Long, seconds: Int) {
        executor.schedule(
            { bot.sendMessage(chatId = chatId, text = "Answering after $seconds seconds delay") },
            seconds.toLong(),
            TimeUnit.SECONDS
        )
    }
}