package me.syrym.extension

fun String.containsDigits(): Boolean = this.matches(Regex(".*\\d+.*"))

fun String.containsOnlyDigits(): Boolean = this.matches(Regex("\\d+"))

fun String.isInHMSNotation(): Boolean = this.matches(Regex("\\d+h|\\d+m|\\d+s"))

fun String.removeAllNonDigit(): String = this.replace(Regex("[^\\d]"), "")

fun String.getHours(): Int {
    val s = this.replace(Regex("[0-9]+h"), "")
    return if (s.isEmpty()) 0 else s.toInt()
}

fun String.getMinutes(): Int {
    val s = this.replace(Regex("[0-9]+m"), "")
    return if (s.isEmpty()) 0 else s.toInt()
}

fun String.getSeconds(): Int {
    val s = this.replace(Regex("[0-9]+s"), "")
    return if (s.isEmpty()) 0 else s.toInt()
}
