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
}
