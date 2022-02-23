package exam

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Task1Test {

    @Test
    fun getMaxBaseCollage() {
        assertEquals("XYXYXY", getMaxBaseCollage(3, 3))
        assertEquals("XYXYXYYY", getMaxBaseCollage(3, 5))
        assertEquals("XXX", getMaxBaseCollage(3, 0))
        assertEquals("YYY", getMaxBaseCollage(0, 3))
        assertEquals("XYXX", getMaxBaseCollage(3, 1))
    }
}