package me.syrym.configuration

import com.natpryce.konfig.*
import com.natpryce.konfig.ConfigurationProperties.Companion.systemProperties
import me.syrym.service.AnswerSchedulerService
import me.syrym.service.impl.AnswerSchedulerServiceImpl
import me.syrym.service.impl.MessageProcessingServiceImpl
import org.koin.dsl.module

val messageFormats = Key("message.formats", stringType)
val messageWelcome = Key("message.welcome", stringType)
val apiToken = Key("token", stringType)
val corePoolSize= Key("core.pool.size", intType)

val config = systemProperties() overriding
        EnvironmentVariables() overriding
        ConfigurationProperties.fromResource("application.properties")
