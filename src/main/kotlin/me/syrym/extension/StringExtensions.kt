package me.syrym.extension

fun String.containsDigits(): Boolean = this.matches(Regex(".*\\d+.*"))

fun String.containsOnlyDigits(): Boolean = this.matches(Regex("\\d+"))

/*
    HMS notation example: 2d 10m 43s
 */
fun String.isInHMSNotation(): Boolean = this.matches(Regex("\\d+h|\\d+m|\\d+s"))

fun String.removeAllNonDigit(): String = this.replace(Regex("[^\\d:]"), "")

fun String.getHours(): Int {
    val regex = "\\d+h".toRegex()
    val res = regex.find(this)?.value
    if (res != null) {
        return res.substring(0, res.length - 1).toInt()
    }
    return 0
}

fun String.getMinutes(): Int {
    val regex = "\\d+m".toRegex()
    val res = regex.find(this)?.value
    if (res != null) {
        return res.substring(0, res.length - 1).toInt()
    }
    return 0
}

fun String.getSeconds(): Int {
    val regex = "\\d+s".toRegex()
    val res = regex.find(this)?.value
    if (res != null) {
        return res.substring(0, res.length - 1).toInt()
    }
    return 0
}
