package exam

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Task3Test {

    @Test
    fun minTimeTest() {
        assertEquals(12, minTime(arrayOf(0, 5, 2, 5, 20, 20), arrayOf(0, 10, 10, 5, 20, 1), arrayOf(0, 15, 15, 5, 1, 1)))
        assertEquals(11, minTime(arrayOf(0, 10, 5, 1), arrayOf(0, 15, 1, 1), arrayOf(0, 14, 1, 1)))
        assertEquals(3, minTime(arrayOf(0, 1, 1, 1, 20 , 1, 1 ), arrayOf(0, 1, 1, 1, 40 , 1, 1 ), arrayOf(0, 1, 1, 1, 60 , 1, 1 )))
    }
}