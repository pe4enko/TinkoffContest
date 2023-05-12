package ya.test.sprint8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * В этой задаче вам необходимо посчитать префикс-функцию для заданной строки.
 * <p>
 * Формат ввода На вход подаётся строка, состоящая из строчных латинских букв. Длина строки не превосходит 106.
 * <p>
 * Формат вывода Если длина входной строки L, то выведите через пробел L целых неотрицательных чисел —– массив значений
 * префикс-функции исходной строки.
 */
public class L_Подсчёт_префикс_функции {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String s = reader.readLine();

            int[] p = new int[s.length()];

            for (int i = 1; i < s.length(); i++) {
                int k = p[i - 1];

                while (k > 0 && (s.charAt(k) != s.charAt(i))) {
                    k = p[k - 1];
                }

                if (s.charAt(k) == s.charAt(i)) {
                    k++;
                }

                p[i] = k;
            }

            writer.write(Arrays.stream(p)
                    .mapToObj(Objects::toString)
                    .collect(Collectors.joining(" ")));
            writer.flush();
        }
    }
    
/*
0 1 2 3 4 5 6 7 8 9 10
0 0 0 1 0 1 0 1 2 3 4


0123456789
abracadabra
       abracadabra

      0 1 2 3 4 5 6 7 8 9 10
p[] = 0

i = 1
k = 0


*/


}
