package me.syrym.extension

fun String.containsDigits(): Boolean = this.matches(Regex(".*\\d+.*"))

fun String.containsOnlyDigits(): Boolean = this.matches(Regex("\\d+"))

fun String.removeAllNonDigit(): String = this.replace(Regex("[^\\d:]"), "")
