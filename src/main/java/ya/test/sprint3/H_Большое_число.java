package ya.test.sprint3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Вечером ребята решили поиграть в игру «Большое число». Даны числа. Нужно определить, какое самое большое число можно
 * из них составить.
 * <p>
 * Формат ввода В первой строке записано n — количество чисел. Оно не превосходит 100. Во второй строке через пробел
 * записаны n неотрицательных чисел, каждое из которых не превосходит 1000.
 * <p>
 * Формат вывода Нужно вывести самое большое число, которое можно составить из данных чисел.
 */
public class H_Большое_число {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            String[] arr = readStringArray(reader);

            Comparator<String> stringComparator = (o1, o2) -> {
                String a = o1 + o2;
                String b = o2 + o1;
                return a.compareTo(b);
            };

            Arrays.sort(arr, Collections.reverseOrder(stringComparator));

            for (String s : arr) {
                writer.write(s);
            }

            writer.flush();
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static String[] readStringArray(BufferedReader reader) throws IOException {
        return reader.readLine().split(" ");
    }
}
