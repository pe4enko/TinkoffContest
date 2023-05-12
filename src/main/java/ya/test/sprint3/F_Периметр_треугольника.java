package ya.test.sprint3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * Перед сном Рита решила поиграть в игру на телефоне. Дан массив целых чисел, в котором каждый элемент обозначает длину
 * стороны треугольника. Нужно определить максимально возможный периметр треугольника, составленного из сторон с длинами
 * из заданного массива. Помогите Рите скорее закончить игру и пойти спать.
 * <p>
 * Напомним, что из трёх отрезков с длинами a ≤ b ≤ c можно составить треугольник, если выполнено неравенство
 * треугольника: c < a + b
 * <p>
 * Разберём пример: даны длины сторон 6, 3, 3, 2. Попробуем в качестве наибольшей стороны выбрать 6. Неравенство
 * треугольника не может выполниться, так как остались 3, 3, 2 —– максимальная сумма из них равна 6.
 * <p>
 * Без шестёрки оставшиеся три отрезка уже образуют треугольник со сторонами 3, 3, 2. Неравенство выполняется: 3 < 3 +
 * 2. Периметр равен 3 + 3 + 2 = 8.
 * <p>
 * Формат ввода В первой строке записано количество отрезков n, 3≤ n≤ 10000.
 * <p>
 * Во второй строке записано n натуральных чисел, не превосходящих 10 000, –— длины отрезков.
 * <p>
 * Формат вывода Нужно вывести одно число —– наибольший периметр треугольника.
 */
public class F_Периметр_треугольника {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            if (n < 3) {
                return;
            }

            int[] sides = readArray(reader);

            Arrays.sort(sides);

            int maxPerimeter = 0;

            for (int i = 2; i < sides.length; i++) {
                int a = sides[i - 2];
                int b = sides[i - 1];
                int c = sides[i];

                if (c >= a + b) {
                    continue;
                }
                int perimeter = a + b + c;
                if (maxPerimeter < perimeter) {
                    maxPerimeter = perimeter;
                }

            }

            writer.write(String.valueOf(maxPerimeter));
            writer.flush();
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
