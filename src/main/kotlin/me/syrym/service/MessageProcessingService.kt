package me.syrym.service

import me.ivmg.telegram.Bot
import me.ivmg.telegram.entities.Update

interface MessageProcessingService {
    fun processMessage(bot: Bot, update: Update)
    fun greet(bot: Bot, update: Update)
}