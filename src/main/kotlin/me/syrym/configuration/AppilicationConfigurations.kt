package me.syrym.configuration

import com.natpryce.konfig.*
import com.natpryce.konfig.ConfigurationProperties.Companion.systemProperties

val messageFormats = Key("message.formats", stringType)
val messageWelcome = Key("message.welcome", stringType)
val apiToken = Key("token", stringType)
val corePoolSize = Key("core.pool.size", intType)
val errMessage = Key("message.error", stringType)

val config = systemProperties() overriding
        EnvironmentVariables() overriding
        ConfigurationProperties.fromResource("application.properties")
