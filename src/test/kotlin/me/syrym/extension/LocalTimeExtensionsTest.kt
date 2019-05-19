package me.syrym.extension

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalTime

class LocalTimeExtensionsTest {

    @Test
    fun `convertToSeconds`() {
        val localTime = LocalTime.of(1, 10, 23)
        Assertions.assertEquals(1 * 60 * 60 + 10 * 60 + 23, localTime.convertToSeconds())
    }
}