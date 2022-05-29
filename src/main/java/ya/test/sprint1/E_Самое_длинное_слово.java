package ya.test.sprint1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Чтобы подготовиться к семинару, Гоше надо прочитать статью по эффективному менеджменту. Так как Гоша хочет
 * спланировать день заранее, ему необходимо оценить сложность статьи.
 * <p>
 * Он придумал такой метод оценки: берётся случайное предложение из текста и в нём ищется самое длинное слово. Его длина
 * и будет условной сложностью статьи.
 * <p>
 * Помогите Гоше справиться с этой задачей.
 * <p>
 * Формат ввода В первой строке дана длина текста L (1 ≤ L ≤ 105).
 * <p>
 * В следующей строке записан текст, состоящий из строчных латинских букв и пробелов. Слово —– последовательность букв,
 * не разделённых пробелами. Пробелы могут стоять в самом начале строки и в самом её конце. Текст заканчивается
 * переносом строки, этот символ не включается в число остальных L символов.
 * <p>
 * Формат вывода В первой строке выведите самое длинное слово. Во второй строке выведите его длину. Если подходящих слов
 * несколько, выведите то, которое встречается раньше.
 */
public class E_Самое_длинное_слово {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int l = readInt(reader);
            String[] str = readList(reader);

            String resultStr = "";
            int maxLength = 0;
            for (String s : str) {
                int length = s.length();
                if (length > maxLength) {
                    maxLength = length;
                    resultStr = s;
                }
            }

            writer.write(resultStr + "\n" + maxLength);
            writer.flush();
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static String[] readList(BufferedReader reader) throws IOException {
        return reader.readLine().split(" ");
    }
}
