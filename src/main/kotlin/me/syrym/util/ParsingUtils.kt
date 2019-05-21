package me.syrym.util

import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun parseHHMMSSFormatStringToLocalTime(text: String): LocalTime = LocalTime.parse(
    text,
    DateTimeFormatter.ofPattern("HH:mm:ss")
)

fun getHours(text: String): Int {
    val regex = "\\d+h".toRegex()
    val res = regex.find(text)?.value
    if (res != null) {
        return res.substring(0, res.length - 1).toInt()
    }
    return 0
}

fun getMinutes(text: String): Int {
    val regex = "\\d+m".toRegex()
    val res = regex.find(text)?.value
    if (res != null) {
        return res.substring(0, res.length - 1).toInt()
    }
    return 0
}

fun getSeconds(text: String): Int {
    val regex = "\\d+s".toRegex()
    val res = regex.find(text)?.value
    if (res != null) {
        return res.substring(0, res.length - 1).toInt()
    }
    return 0
}
