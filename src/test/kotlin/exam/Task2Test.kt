package exam

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Task2Test {

    @Test
    fun checkSecurityPathTest() {
        assertEquals("YES", checkSecurityPath(2, listOf(true, false, true, false, true)))
        assertEquals("YES", checkSecurityPath(1, listOf(true, true)))
        assertEquals("NO", checkSecurityPath(1, listOf(true, false, true)))
        assertEquals("NO", checkSecurityPath(2, listOf(true, false, false, true)))
        assertEquals("YES", checkSecurityPath(2, listOf(true, true, false, true)))
        assertEquals("NO", checkSecurityPath(2, listOf(true, false)))
    }
}