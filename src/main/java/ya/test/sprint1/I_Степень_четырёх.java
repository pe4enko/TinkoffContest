package ya.test.sprint1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Вася на уроке математики изучил степени. Теперь он хочет написать программу, которая определяет, будет ли
 * положительное целое число степенью четвёрки.
 * <p>
 * Подсказка: степенью четвёрки будут все числа вида 4n, где n – целое неотрицательное число.
 * <p>
 * Формат ввода На вход подаётся целое число в диапазоне от 1 до 10000.
 * <p>
 * Формат вывода Выведите «True», если число является степенью четырёх, «False» –— в обратном случае.
 */
public class I_Степень_четырёх {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);

            if (n == 1 || n == 4 || n == 16 || n == 64 || n == 256 || n == 1024 || n == 4096) {
                writer.write("True");
                writer.flush();
            } else {
                writer.write("False");
                writer.flush();
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
