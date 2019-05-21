package me.syrym.util

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalTime

class ParsingUtilsTest {

    @Test
    fun `test parseHHMMSSFormatStringToLocalTime for full time format`() {
        val text = "22:10:22"
        Assertions.assertEquals(LocalTime.of(22, 10, 22), parseHHMMSSFormatStringToLocalTime(text))
    }

    @Test
    fun `test parseHHMMSSFormatStringToLocalTime for minutes and seconds`() {
        val text = "00:10:22"
        Assertions.assertEquals(LocalTime.of(0, 10, 22), parseHHMMSSFormatStringToLocalTime(text))
    }

    @Test
    fun `test getHours for noisy string`() {
        val text = "asd 1h asd"
        Assertions.assertEquals(1, getHours(text))
    }

    @Test
    fun `test getHours for correct format`() {
        val text = "2h 10m 11s"
        Assertions.assertEquals(2, getHours(text))
    }

    @Test
    fun `test getMinutes for noisy string`() {
        val text = "asd 23m asd"
        Assertions.assertEquals(23, getMinutes(text))
    }

    @Test
    fun `test getSeconds for noisy string`() {
        val text = "asd 55s asd"
        Assertions.assertEquals(55, getSeconds(text))
    }
}