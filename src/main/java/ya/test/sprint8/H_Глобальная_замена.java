package ya.test.sprint8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Напишите программу, которая будет заменять в тексте все вхождения строки s на строку t. Гарантируется, что никакие
 * два вхождения шаблона s не пересекаются друг с другом.
 * <p>
 * Формат ввода В первой строке дан текст —– это строка из строчных букв английского алфавита, длина которой не
 * превышает 106.
 * <p>
 * Во второй строке записан шаблон s, вхождения которого будут заменены.
 * <p>
 * В третьей строке дана строка t, которая будет заменять вхождения.
 * <p>
 * Обе строки s и t состоят из строчных букв английского алфавита, длина каждой строки не превосходит 105. Размер
 * итоговой строки не превосходит 2⋅ 106.
 * <p>
 * Формат вывода В единственной строке выведите результат всех замен — текст, в котором все вхождения s заменены на t.
 */
public class H_Глобальная_замена {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String str = reader.readLine();
            String template = reader.readLine();
            String replace = reader.readLine();

            int templateLength = template.length();

            String s = template + "#" + str;

            int[] p = new int[s.length()];

            int printPosition = 0;

            for (int i = 1; i < s.length(); i++) {
                int k = p[i - 1];

                while (k > 0 && (s.charAt(k) != s.charAt(i))) {
                    k = p[k - 1];
                }

                if (s.charAt(k) == s.charAt(i)) {
                    k++;
                }

                p[i] = k;

                if (k == templateLength) {
                    String substring = str.substring(printPosition, i - templateLength - templateLength);
                    writer.write(substring + replace);
                    printPosition = i - templateLength;
                }
            }

            String substring = str.substring(printPosition);
            writer.write(substring);

            writer.flush();
        }
    }
}
