package ya.test.sprint4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Жители Алгосов любят устраивать турниры по спортивному программированию. Все участники разбиваются на пары и
 * соревнуются друг с другом. А потом два самых сильных программиста встречаются в финальной схватке, которая состоит из
 * нескольких раундов. Если в очередном раунде выигрывает первый участник, в таблицу с результатами записывается 0, если
 * второй, то 1. Ничьей в раунде быть не может.
 * <p>
 * Нужно определить наибольший по длине непрерывный отрезок раундов, по результатам которого суммарно получается ничья.
 * Например, если дана последовательность 0 0 1 0 1 1 1 0 0 0, то раунды с 2-го по 9-й (нумерация начинается с единицы)
 * дают ничью.
 * <p>
 * Формат ввода В первой строке задаётся n (0 ≤ n ≤ 105) –— количество раундов. Во второй строке через пробел записано n
 * чисел –— результаты раундов. Каждое число равно либо 0, либо 1.
 * <p>
 * Формат вывода Выведите длину найденного отрезка.
 */
public class G_Соревнование {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);

            if (n == 0 || n == 1) {
                writer.write(String.valueOf(0));
                writer.flush();
                System.exit(0);
            }

            int[] results = readIntArrayWithSize(n, reader);
            int[] sums = new int[n];
            sums[0] = results[0];

            for (int i = 1; i < results.length; i++) {
                int result = results[i];
                sums[i] = sums[i - 1] + result;
            }

            HashMap<Integer, FirstLast> positions = new HashMap<>();
            positions.put(0, new FirstLast(-1, -1));

            for (int i = 0; i < sums.length; i++) {
                int sum = sums[i];
                FirstLast firstLast = positions.get(sum);
                if(firstLast == null) {
                    positions.put(sum, new FirstLast(i, -1));
                } else {
                    firstLast.setLastPosition(i);
                }
            }

            int max = 0;
            for (FirstLast firstLast : positions.values()) {
                if (firstLast.lastPosition == -1) {
                    continue;
                }

                int maxTmp = firstLast.lastPosition - firstLast.firstPosition;

                if (maxTmp > max) {
                    max = maxTmp;
                }
            }

            writer.write(String.valueOf(max));
            writer.flush();
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readIntArrayWithSize(int size, BufferedReader reader) throws IOException {

        final int[] result = new int[size];
        int i = 0;

        StringTokenizer st = new StringTokenizer(reader.readLine());
        while (st.hasMoreTokens()) {
            int winner = Integer.parseInt(st.nextToken());
            result[i] = winner > 0 ? 1 : -1;
            i++;
        }

        return result;
    }

    private static class FirstLast {
        int firstPosition = -1;
        int lastPosition = -1;

        public FirstLast(int firstPosition, int lastPosition) {
            this.firstPosition = firstPosition;
            this.lastPosition = lastPosition;
        }

        public int getFirstPosition() {
            return firstPosition;
        }

        public void setFirstPosition(int firstPosition) {
            this.firstPosition = firstPosition;
        }

        public int getLastPosition() {
            return lastPosition;
        }

        public void setLastPosition(int lastPosition) {
            this.lastPosition = lastPosition;
        }
    }
}
