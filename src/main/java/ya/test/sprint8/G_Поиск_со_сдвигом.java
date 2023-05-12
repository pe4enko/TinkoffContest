package ya.test.sprint8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Гоша измерял температуру воздуха n дней подряд. В результате у него получился некоторый временной ряд. Теперь он
 * хочет посмотреть, как часто встречается некоторый шаблон в получившейся последовательности. Однако температура — вещь
 * относительная, поэтому Гоша решил, что при поиске шаблона длины m (a1, a2, ..., am) стоит также рассматривать
 * сдвинутые на константу вхождения. Это значит, что если для некоторого числа c в исходной последовательности нашёлся
 * участок вида (a1 + c, a2 + c, ... , am + c), то он тоже считается вхождением шаблона (a1, a2, ..., am).
 * <p>
 * По заданной последовательности измерений X и шаблону A=(a1, a2, ..., am) определите все вхождения A в X, допускающие
 * сдвиг на константу.
 * <p>
 * Подсказка: если вы пишете на питоне и сталкиваетесь с TL, то попробуйте заменить какие-то из циклов операциями со
 * срезами.
 * <p>
 * Формат ввода В первой строке дано количество сделанных измерений n — натуральное число, не превышающее 104. Во второй
 * строке через пробел записаны n целых чисел xi, 0 ≤ xi ≤ 103 –— результаты измерений. В третьей строке дано
 * натуральное число m –— длина искомого шаблона, 1≤ m ≤ n. В четвёртой строке даны m целых чисел ai — элементы шаблона,
 * 0 ≤ ai ≤ 103.
 * <p>
 * Формат вывода Выведите через пробел в порядке возрастания все позиции, на которых начинаются вхождения шаблона A в
 * последовательность X. Нумерация позиций начинается с единицы.
 */
public class G_Поиск_со_сдвигом {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            int[] measurements = readIntArrayWithSize(n, reader);

            int m = readInt(reader);
            int[] template = readIntArrayWithSize(m, reader);

            if (template.length > measurements.length) {
                return;
            }

            for (int i = 0; i <= n - template.length; i++) {
                int shift = measurements[i] - template[0];

                boolean findTemplate = true;
                boolean findTemplateWithShift = true;

                for (int j = 0; j < m; j++) {
                    int a = measurements[i + j];
                    int b = template[j];

                    if (a != b) {
                        findTemplate = false;
                    }

                    if (b + shift != a) {
                        findTemplateWithShift = false;
                    }

                    if (!findTemplate && !findTemplateWithShift) {
                        break;
                    }
                }

                if (findTemplate || findTemplateWithShift) {
                    writer.write(i + 1 + " ");
                }
            }

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
            result[i] = Integer.parseInt(st.nextToken());
            i++;
        }

        return result;
    }
}
