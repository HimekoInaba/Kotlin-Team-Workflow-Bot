package me.syrym.extension

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class StringExtensionsTest {

    @Test
    fun `test containsDigits`() {
        val text = "22ss33"
        Assertions.assertTrue(text.containsDigits())
    }

    @Test
    fun `test containsDigits false`() {
        val text = "adsascxxx"
        Assertions.assertFalse(text.containsDigits())
    }

    @Test
    fun `test containsOnlyDigits`() {
        val text = "623424"
        Assertions.assertTrue(text.containsOnlyDigits())
    }

    @Test
    fun `test containsOnlyDigits false`() {
        val text = "adsas22cxxx"
        Assertions.assertFalse(text.containsOnlyDigits())
    }

    @Test
    fun `test removeAllNonDigit`() {
        val text = "adsas22c88xxx"
        Assertions.assertTrue(text.removeAllNonDigit().containsOnlyDigits())
    }

    @Test
    fun `getHours`() {
        val text = "asd 1h asd"
        Assertions.assertEquals(1, text.getHours())
    }
}
