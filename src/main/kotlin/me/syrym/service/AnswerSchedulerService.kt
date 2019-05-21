package me.syrym.service

interface AnswerSchedulerService {
    fun scheduleBotResponse(chatId: Long, seconds: Int)
}