package ya.test.sprint7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * Черепаха Кондратина путешествует по клетчатому полю из n строк и m столбцов. В каждой клетке либо растёт цветочек,
 * либо не растёт. Кондратине надо добраться из левого нижнего в правый верхний угол и собрать как можно больше
 * цветочков.
 * <p>
 * Помогите ей с этой сложной задачей и определите, какое наибольшее число цветочков она сможет собрать при условии, что
 * Кондратина умеет передвигаться только на одну клетку вверх или на одну клетку вправо за ход.
 * <p>
 * Формат ввода В первой строке даны размеры поля n и m (через пробел). Оба числа лежат в диапазоне от 1 до 1000. В
 * следующих n строках задано поле. Каждая строка состоит из m символов 0 или 1, записанных подряд без пробелов, и
 * завершается переводом строки. Если в клетке записана единица, то в ней растёт цветочек.
 * <p>
 * Формат вывода Выведите единственное число — максимальное количество цветочков, которое сможет собрать Кондратина.
 */
public class H_Поле_с_цветочками {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            StringTokenizer st = new StringTokenizer(reader.readLine());

            int rows = Integer.parseInt(st.nextToken());
            int columns = Integer.parseInt(st.nextToken());

            boolean[][] field = readArrayWithSize(rows, columns, reader);
            int[][] flowersCount = new int[rows + 1][columns + 1];

            //Обходить матрицу сначала по строкам, потом по столбцам.

            for (int r = 1; r <= rows; r++) {
                for (int c = 1; c <= columns; c++) {
                    int left = flowersCount[r][c - 1];
                    int under = flowersCount[r - 1][c];

                    flowersCount[r][c] = Math.max(left, under) + (field[r - 1][c - 1] ? 1 : 0);
                }
            }

/*
            for (int c = 1; c <= columns; c++) {
                for (int r = 1; r <= rows; r++) {
                    int left = flowersCount[r][c-1];
                    int under = flowersCount[r-1][c];

                    flowersCount[r][c] = Math.max(left, under) + (field[r - 1][c - 1] ? 1 : 0);
                }
            }
*/

            writer.write(String.valueOf(flowersCount[rows][columns]));
            writer.flush();
        }
    }

    private static boolean[][] readArrayWithSize(int rows, int columns, BufferedReader reader) throws IOException {

        boolean[][] result = new boolean[rows][columns];

        for (int i = rows - 1; i >= 0; i--) {
            String str = reader.readLine();
            for (int j = 0; j < columns; j++) {
                boolean hasFlower = str.charAt(j) != '0';
                result[i][j] = hasFlower;
            }
        }

        return result;
    }
}
