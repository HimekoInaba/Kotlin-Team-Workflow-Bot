package me.syrym.service

import me.ivmg.telegram.entities.Update

interface MessageProcessingService {
    fun processMessage(update: Update)
}
