package ya.test.sprint3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Гоша любит играть в игру «Подпоследовательность»: даны 2 строки, и нужно понять, является ли первая из них
 * подпоследовательностью второй. Когда строки достаточно длинные, очень трудно получить ответ на этот вопрос, просто
 * посмотрев на них. Помогите Гоше написать функцию, которая решает эту задачу.
 * <p>
 * Формат ввода В первой строке записана строка s.
 * <p>
 * Во второй —- строка t.
 * <p>
 * Обе строки состоят из маленьких латинских букв, длины строк не превосходят 150000. Строки не могут быть пустыми.
 * <p>
 * Формат вывода Выведите True, если s является подпоследовательностью t, иначе —– False.
 */
public class C_Подпоследовательность {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String str1 = reader.readLine();
            String str2 = reader.readLine();

            boolean subsequence = isSubSequence(str1, str2);

            if (subsequence) {
                writer.write("True");
            } else {
                writer.write("False");
            }

            writer.flush();
        }
    }

    private static boolean isSubSequence(String str1, String str2) {
        if (str1.length() <= 0) {
            return true;
        }
        if (str1.length() > str2.length()) {
            return false;
        }

        int i = 0;
        int j = 0;

        while (i < str1.length() && j < str2.length()) {
            char s1 = str1.charAt(i);
            char s2 = str2.charAt(j);

            if (s1 == s2) {
                i++;
                j++;
            } else {
                boolean foundInStr2 = false;

                while (j + 1 < str2.length()) {
                    j++;
                    s2 = str2.charAt(j);

                    if (s1 == s2) {
                        foundInStr2 = true;
                        i++;
                        j++;
                        break;
                    }
                }

                if (!foundInStr2) {
                    return false;
                }
            }
        }

        //Случай когда str2 закончился, а str1 еще нет.
        if (i < str1.length()) {
            return false;
        }

        //Случай когда строки равны.
        return true;
    }

    private static boolean isSubSequence2(String str1, String str2) {
        if (str1.length() <= 0) {
            return true;
        }
        if (str1.length() > str2.length()) {
            return false;
        }

        int i = 0;
        int j = 0;

        while (i < str1.length() && j < str2.length()) {
            if (str1.charAt(i) == str2.charAt(j)) {
                i++;
                j++;

                if (i == str1.length()) {
                    return true;
                }
            } else {
                j++;
                if (j == str2.length()) {
                    return false;
                }
            }
        }
        return false;
    }
}
