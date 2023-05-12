package ya.test.sprint7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Теперь черепашке Кондратине надо узнать не только, сколько цветочков она может собрать, но и как ей построить свой
 * маршрут для этого. Помогите ей!
 * <p>
 * Напомним, что Кондратине надо дойти от левого нижнего до правого верхнего угла, а передвигаться она умеет только
 * вверх и вправо.
 * <p>
 * Формат ввода В первой строке даны размеры поля n и m (через пробел). Оба числа лежат в диапазоне от 1 до 1000. В
 * следующих n строках задано поле. Каждая строка состоит из m символов 0 или 1 и завершается переводом строки. Если в
 * клетке записана единица, то в ней растет цветочек.
 * <p>
 * Формат вывода Выведите в первой строке максимальное количество цветочков, которое сможет собрать Кондратина. Во
 * второй строке выведите маршрут в виде последовательности символов «U» и «R», где «U» означает передвижение вверх, а
 * «R» – передвижение вправо.
 * <p>
 * Если возможных оптимальных путей несколько, то выведите любой.
 */
public class I_Сложное_поле_с_цветочками {

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

            int r = rows;
            int c = columns;

            LinkedList<Character> path = new LinkedList<>();
            while (r > 1 || c > 1) {
                int l = flowersCount[r][c - 1];
                int u = flowersCount[r - 1][c];

                if (l >= u && c != 1) {
                    path.addFirst('R');
                    c -= 1;
                } else {
                    path.addFirst('U');
                    r -= 1;
                }
            }

            writer.write(flowersCount[rows][columns] + "\n");

            for (Character character : path) {
                writer.write(character);
            }

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
