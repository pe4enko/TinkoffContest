import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Test3 {

    @Test
    fun calcTest() {
//    dogovorCount (n)  - Кол-во договоров, которое надо разнести (всего сотрудников) 2 ≤ n
//    timeOut      (t)  - через это время уйдет нужный сотрудник                      t ≤ 100

        assertEquals(24, calc(5, 2, listOf(1, 4, 9, 16, 25)))
        assertEquals(31, calc(4, 5, listOf(1, 2, 3, 6, 8, 25)))
        assertEquals(2, calc(1, 3, listOf(1, 2, 3)))
        assertEquals(1, calc(100, 1, listOf(1, 2)))
        assertEquals(1, calc(100, 2, listOf(1, 2)))
        assertEquals(4, calc(2, 4, listOf(1, 2, 3, 4, 5)))
        assertEquals(4, calc(2, 2, listOf(1, 2, 3, 4, 5)))
    }
}