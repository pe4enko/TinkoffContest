package ya.test.sprint1final;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://contest.yandex.ru/contest/22450/run-report/68662384/
 * <p>
 * Гоша и Тимофей нашли необычный тренажёр для скоростной печати и хотят освоить его. Тренажёр представляет собой поле
 * из клавиш 4× 4, в котором на каждом раунде появляется конфигурация цифр и точек. На клавише написана либо точка, либо
 * цифра от 1 до 9. В момент времени t игрок должен одновременно нажать на все клавиши, на которых написана цифра t.
 * Гоша и Тимофей могут нажать в один момент времени на k клавиш каждый. Если в момент времени t были нажаты все нужные
 * клавиши, то игроки получают 1 балл.
 * <p>
 * Найдите число баллов, которое смогут заработать Гоша и Тимофей, если будут нажимать на клавиши вдвоём.
 * <p>
 * <p>
 * <p>
 * Формат ввода В первой строке дано целое число k (1 ≤ k ≤ 5).
 * <p>
 * В четырёх следующих строках задан вид тренажёра –— по 4 символа в каждой строке. Каждый символ —– либо точка, либо
 * цифра от 1 до 9. Символы одной строки идут подряд и не разделены пробелами.
 * <p>
 * Формат вывода Выведите единственное число –— максимальное количество баллов, которое смогут набрать Гоша и Тимофей.
 */
public class B_Ловкость_рук {

    public static int checkPoints(int canPushSameTime, char[][] configuration) {
        int[] meetTimes = new int[9];

        for (char[] chars : configuration) {
            for (char c : chars) {
                if (Character.isDigit(c)) {
                    meetTimes[c - '1']++;
                }
            }
        }

        int points = 0;

        for (int meetTime : meetTimes) {
            if (meetTime > 0 && meetTime <= canPushSameTime) {
                points++;
            }
        }

        return points;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            char[][] configuration = readCharMatrix(reader, 4);

            int points = checkPoints(n * 2, configuration);

            writer.write(String.valueOf(points));
            writer.flush();
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static char[][] readCharMatrix(BufferedReader reader, int times) throws IOException {
        char[][] matrix = new char[times][times];

        for (int i = 0; i < times; i++) {
            matrix[i] = reader.readLine().toCharArray();
        }

        return matrix;
    }
}
