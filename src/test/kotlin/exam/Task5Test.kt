package exam

import org.junit.jupiter.api.Test

internal class Task5Test {

    @Test
    fun arrangeTest() {
        assert(arrayOf(1, 2, 1, 3, -1).contentEquals(minTime(3, 5, 5, arrayOf(2, 4, 3, 3, 3))))
        assert(arrayOf(-1).contentEquals(minTime(2, 1, 1, arrayOf(2))))
        assert(arrayOf(1).contentEquals(minTime(2, 1, 1, arrayOf(1))))
        assert(arrayOf(1).contentEquals(minTime(2, 1, 1, arrayOf(1))))
        assert(arrayOf(1, 2).contentEquals(minTime(2, 2, 2, arrayOf(1, 2))))
        assert(arrayOf(1, 2, -1).contentEquals(minTime(2, 2, 3, arrayOf(1, 2, 2))))
    }
}