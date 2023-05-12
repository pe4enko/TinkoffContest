package ya.test.sprint8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * В некоторых языках предложения пишутся и читаются не слева направо, а справа налево.
 * <p>
 * Вам под руку попался странный текст –— в нём обычный (слева направо) порядок букв в словах. А вот сами слова идут в
 * противоположном направлении. Вам надо преобразовать текст так, чтобы слова в нём были написаны слева направо.
 * <p>
 * Формат ввода На ввод подаётся строка, состоящая из слов, разделённых пробелами (один пробел между соседними словами).
 * Всего слов не более 1000, длина каждого из них —– от 1 до 100 символов. Слова состоят из строчных букв английского
 * алфавита.
 * <p>
 * Формат вывода Выведите строку с обратным порядком слов в ней.
 */
public class A_Разворот_строки {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String s = reader.readLine();

            String[] split = s.split("\\s");

            for (int i = split.length - 1; i >= 0; i--) {
                String s1 = split[i];
                writer.write(s1);
                writer.write(" ");
            }

            writer.flush();
        }

    }
}
