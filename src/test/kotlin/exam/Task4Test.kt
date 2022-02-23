package exam

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Task4Test {

    @Test
    fun nodTest() {
        assertEquals(6, nod(84, 90))
        assertEquals(1, nod(15, 28))
        assertEquals(6, nod(24, 18))
        assertEquals(4, nod(140, 96))
    }

    @Test
    fun nodListTest() {
        assertEquals(6, nod(listOf(84, 90)))
        assertEquals(6, nod(listOf(12, 6, 42, 18)))
        assertEquals(1, nod(listOf(43, 17, 14, 51)))
        assertEquals(2, nod(listOf(56, 12, 44, 14)))
    }

    @Test
    fun isNodsAGtNodsBTest() {
        assertTrue(isNodsAGeNodsB(listOf(10), listOf(1)))
        assertTrue(isNodsAGeNodsB(listOf(10, 122), listOf(1, 12)))
        assertTrue(isNodsAGeNodsB(listOf(10, 122), listOf(10, 12)))
        assertTrue(isNodsAGeNodsB(listOf(10, 2), listOf(10, 1)))
        assertFalse(isNodsAGeNodsB(listOf(10, 2), listOf(10, 3)))
        assertFalse(isNodsAGeNodsB(listOf(3, 2), listOf(10, 3)))
    }

    @Test
    fun testKaif() {
//        assertEquals(listOf(5, 2), generateMaxKaifForArina(mutableListOf(2, 5)))
//        assertEquals(listOf(8, 2, 1, 3), generateMaxKaifForArina(mutableListOf(1, 8, 2, 3))) //как в примере
//        assertEquals(listOf(8, 2, 3, 1), generateMaxKaifForArina(mutableListOf(1, 8, 2, 3)))
//        assertEquals(listOf(9, 3, 8), generateMaxKaifForArina(mutableListOf(3, 8, 9)))
//        assertEquals(listOf(100, 50, 25, 75, 64), generateMaxKaifForArina(mutableListOf(64, 25, 75, 100, 50)))
//        assertEquals(listOf(42), generateMaxKaifForArina(mutableListOf(42)))
//        assertEquals(listOf(128, 96, 80, 88, 52, 7), generateMaxKaifForArina(mutableListOf(96, 128, 88, 80, 52, 7)))
//        assertEquals(listOf(17, 2, 4, 8, 16), generateMaxKaifForArina(mutableListOf(2, 4, 8, 16, 17)))
//        assertEquals(listOf(34, 2, 4, 8, 16), generateMaxKaifForArina(mutableListOf(2, 4, 8, 16, 34)))

        assertEquals(listOf(5, 2), generateMaxKaifForArina2(mutableListOf(2, 5)))
        assertEquals(listOf(8, 2, 1, 3), generateMaxKaifForArina2(mutableListOf(1, 8, 2, 3)))
        assertEquals(listOf(9, 3, 8), generateMaxKaifForArina2(mutableListOf(3, 8, 9)))
        assertEquals(listOf(100, 50, 25, 75, 64), generateMaxKaifForArina2(mutableListOf(64, 25, 75, 100, 50)))
        assertEquals(listOf(42), generateMaxKaifForArina2(mutableListOf(42)))
        assertEquals(listOf(128, 96, 80, 88, 52, 7), generateMaxKaifForArina2(mutableListOf(96, 128, 88, 80, 52, 7)))
        assertEquals(listOf(17, 2, 4, 8, 16), generateMaxKaifForArina2(mutableListOf(2, 4, 8, 16, 17)))
        assertEquals(listOf(34, 2, 4, 8, 16), generateMaxKaifForArina2(mutableListOf(2, 4, 8, 16, 34)))


    }
}