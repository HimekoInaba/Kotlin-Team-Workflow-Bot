package me.syrym.extension

import java.time.LocalTime

fun LocalTime.convertToSeconds() = this.hour * 60 * 60 + this.minute * 60 + this.second
