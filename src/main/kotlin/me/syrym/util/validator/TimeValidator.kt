package me.syrym.util.validator

/*
    HMS notation example: 2d 10m 43s
 */
// fun isInHMSNotation(text: String): Boolean = text.matches(Regex("\\d+h|\\d+m|\\d+s"))

fun isInHMSNotation(text: String): Boolean {
    val arr = text.split(Regex("\\s+"))
    var value = true

    for (item in arr) {
        value = value.and(item.matches(Regex("\\d+h|\\d+m|\\d+s")))
    }

    return value
}
