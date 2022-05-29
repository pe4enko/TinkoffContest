package ya.test.sprint1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Помогите Васе понять, будет ли фраза палиндромом‎. Учитываются только буквы и цифры, заглавные и строчные буквы
 * считаются одинаковыми.
 * <p>
 * Решение должно работать за O(N), где N — длина строки на входе.
 * <p>
 * Формат ввода В единственной строке записана фраза или слово. Буквы могут быть только латинские. Длина текста не
 * превосходит 20000 символов.
 * <p>
 * Фраза может состоять из строчных и прописных латинских букв, цифр, знаков препинания.
 * <p>
 * Формат вывода Выведите «True», если фраза является палиндромом, и «False», если не является.
 */
public class F_Палиндром {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String s = reader.readLine();

            int rightPosition = s.length() - 1;
            int leftPosition = 0;

            for (int i = 0; i < s.length() / 2.0; i++) {
                char leftChar;
                do {
                    leftChar = s.charAt(leftPosition++);
                } while (isSpace(leftChar));

                char rightChar;
                do {
                    rightChar = s.charAt(rightPosition--);
                } while (isSpace(rightChar));

                if (Character.toLowerCase(leftChar) != Character.toLowerCase(rightChar)) {
                    writer.write("False");
                    writer.flush();
                    System.exit(0);
                }
            }

            writer.write("True");
            writer.flush();
        }
    }

    /**
     * 65-90 97-122
     */
    private static boolean isSpace(char c) {
        return !((c >= 65 && c <= 90) || (c >= 97 && c <= 122));
    }
}
