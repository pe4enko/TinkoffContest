package ya.test.sprint1final;

import static ya.test.sprint1final.B_Ловкость_рук.checkPoints;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class B_Ловкость_рук_Test {

    @Test
    void checkPointsPrimer1() {
        char[][] configuration = {
                {'1', '2', '3', '1'},
                {'2', '.', '.', '2'},
                {'2', '.', '.', '2'},
                {'2', '.', '.', '2'}
        };
        Assertions.assertEquals(2, checkPoints(6, configuration));
    }

    @Test
    void checkPointsPrimer2() {
        char[][] configuration = {
                {'1', '1', '1', '1'},
                {'9', '9', '9', '9'},
                {'1', '1', '1', '1'},
                {'9', '9', '1', '1'}
        };
        Assertions.assertEquals(1, checkPoints(8, configuration));
    }

    @Test
    void checkPointsPrimer3() {
        char[][] configuration = {
                {'1', '1', '1', '1'},
                {'1', '1', '1', '1'},
                {'1', '1', '1', '1'},
                {'1', '1', '1', '1'}
        };
        Assertions.assertEquals(0, checkPoints(8, configuration));
    }
}