package ya.test.sprint8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Алла придумала новый способ сравнивать две строки: чтобы сравнить строки a и b, в них надо оставить только те буквы,
 * которые в английском алфавите стоят на четных позициях. Затем полученные строки сравниваются по обычным правилам.
 * Помогите Алле реализовать новое сравнение строк.
 * <p>
 * Формат ввода На вход подаются строки a и b по одной в строке. Обе строки состоят из маленьких латинских букв, не
 * бывают пустыми и не превосходят 105 символов в длину.
 * <p>
 * Формат вывода Выведите -1, если a < b, 0, если a = b, и 1, если a > b.
 */
public class K_Сравнить_две_строки {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String s1 = reader.readLine();
            String s2 = reader.readLine();

            int i = 0;
            int j = 0;

            Character c1ToCompare = null;
            Character c2ToCompare = null;

            while (true) {
                while (i < s1.length()) {
                    char c1 = s1.charAt(i);
                    i++;
                    if (c1 % 2 == 0) {
                        c1ToCompare = c1;
                        break;
                    }
                }

                while (j < s2.length()) {
                    char c2 = s2.charAt(j);
                    j++;
                    if (c2 % 2 == 0) {
                        c2ToCompare = c2;
                        break;
                    }
                }

                if ((i == s1.length() && c1ToCompare == null) && (j == s2.length()) && c2ToCompare == null) {
                    writer.write("0");
                    writer.flush();
                    return;
                } else if (i == s1.length() && c1ToCompare == null) {
                    writer.write("-1");
                    writer.flush();
                    return;
                } else if (j == s2.length() && c2ToCompare == null) {
                    writer.write("1");
                    writer.flush();
                    return;
                }

                int compare = Character.compare(c1ToCompare, c2ToCompare);
                if (compare < 0) {
                    writer.write("-1");
                    writer.flush();
                    return;
                } else if (compare > 0) {
                    writer.write("1");
                    writer.flush();
                    return;
                }

                c1ToCompare = null;
                c2ToCompare = null;
            }
        }
    }
}
