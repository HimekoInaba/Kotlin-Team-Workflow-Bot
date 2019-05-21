package me.syrym.extension

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class StringExtensionsTest {

    @Test
    fun `test containsDigits is true`() {
        val text = "22ss33"
        Assertions.assertTrue(text.containsDigits())
    }

    @Test
    fun `test containsDigits is false`() {
        val text = "adsascxxx"
        Assertions.assertFalse(text.containsDigits())
    }

    @Test
    fun `test containsOnlyDigits is true`() {
        val text = "623424"
        Assertions.assertTrue(text.containsOnlyDigits())
    }

    @Test
    fun `test containsOnlyDigits is false`() {
        val text = "adsas22cxxx"
        Assertions.assertFalse(text.containsOnlyDigits())
    }

    @Test
    fun `test removeAllNonDigit`() {
        val text = "adsas22c88xxx"
        Assertions.assertTrue(text.removeAllNonDigit().containsOnlyDigits())
    }

    @Test
    fun `test isInHMSNotation for full format`() {
        val text = "1h 20m 11s"
        Assertions.assertTrue(text.isInHMSNotation())
    }

    @Test
    fun `test isInHMSNotation for m and s`() {
        val text = "20m 11s"
        Assertions.assertTrue(text.isInHMSNotation())
    }

    @Test
    fun `test getHours for noisy string`() {
        val text = "asd 1h asd"
        Assertions.assertEquals(1, text.getHours())
    }

    @Test
    fun `test getHours for correct format`() {
        val text = "2h 10m 11s"
        Assertions.assertEquals(2, text.getHours())
    }

    @Test
    fun `test getMinutes for noisy string`() {
        val text = "asd 23m asd"
        Assertions.assertEquals(23, text.getMinutes())
    }

    @Test
    fun `test getSeconds for noisy string`() {
        val text = "asd 55s asd"
        Assertions.assertEquals(55, text.getSeconds())
    }
}
