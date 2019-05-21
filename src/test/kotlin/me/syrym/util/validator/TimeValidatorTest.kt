package me.syrym.util.validator

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TimeValidatorTest {

    @Test
    fun `test isInHMSNotation for full format`() {
        val text = "10h 1m 12s"
        Assertions.assertTrue(isInHMSNotation(text))
    }

    @Test
    fun `test isInHMSNotation for m and s`() {
        val text = "1m 20s"
        Assertions.assertTrue(isInHMSNotation(text))
    }

    @Test
    fun `test isInHMSNotation for m and s is false`() {
        val text = "1m 2p"
        Assertions.assertFalse(isInHMSNotation(text))
    }

    @Test
    fun `test isInHMSNotation for m and s is false2`() {
        val text = "1m 2s asd"
        Assertions.assertFalse(isInHMSNotation(text))
    }

    @Test
    fun `test isInHMSNotation for s`() {
        val text = "30s"
        Assertions.assertTrue(isInHMSNotation(text))
    }
}
