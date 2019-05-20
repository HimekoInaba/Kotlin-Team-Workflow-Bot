package me.syrym.di

import me.syrym.service.AnswerSchedulerService
import me.syrym.service.MessageProcessingService
import me.syrym.service.impl.AnswerSchedulerServiceImpl
import me.syrym.service.impl.MessageProcessingServiceImpl
import org.koin.dsl.module

val servicesModule =  module {
    single { AnswerSchedulerServiceImpl() as AnswerSchedulerService }
    single { MessageProcessingServiceImpl(get()) as MessageProcessingService }
}