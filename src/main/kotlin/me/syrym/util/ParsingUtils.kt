package me.syrym.util

import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun parseHHMMSSFormatStringToLocalTime(text: String): LocalTime = LocalTime.parse(
    text,
    DateTimeFormatter.ofPattern("HH:mm:ss")
)
