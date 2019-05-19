package me.syrym.service

import me.ivmg.telegram.Bot

interface AnswerSchedulerService {
    fun scheduleBotResponse(bot: Bot, chatId: Long, seconds: Int)
}