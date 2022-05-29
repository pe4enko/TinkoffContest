package ya.test.sprint1final;

import static ya.test.sprint1final.A_Ближайший_ноль.nearestZero;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class A_Ближайший_ноль_Test {

    @Test
    void test1() {
        int[] houseNumbers = {0, 1, 4, 9, 0, 2, 3};
        int[] expected = {0, 1, 2, 1, 0, 1, 2};

        nearestZero(houseNumbers);

        Assertions.assertArrayEquals(expected, houseNumbers);
    }

    @Test
    void testPrimer1() {
        int[] houseNumbers = {0, 1, 4, 9, 0};
        int[] expected = {0, 1, 2, 1, 0};

        nearestZero(houseNumbers);

        Assertions.assertArrayEquals(expected, houseNumbers);
    }

    @Test
    void testPrimer2() {
        int[] houseNumbers = {0, 7, 9, 4, 8, 20};
        int[] expected = {0, 1, 2, 3, 4, 5};

        nearestZero(houseNumbers);

        Assertions.assertArrayEquals(expected, houseNumbers);
    }

    @Test
    void testAllZero() {
        int[] houseNumbers = {0, 0, 0, 0, 0, 0};
        int[] expected = {0, 0, 0, 0, 0, 0};

        nearestZero(houseNumbers);

        Assertions.assertArrayEquals(expected, houseNumbers);
    }
}