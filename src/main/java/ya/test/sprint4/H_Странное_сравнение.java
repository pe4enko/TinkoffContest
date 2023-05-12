package ya.test.sprint4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Objects;

/**
 * Жители Алгосского архипелага придумали новый способ сравнения строк. Две строки считаются равными, если символы одной
 * из них можно заменить на символы другой так, что первая строка станет точной копией второй строки. При этом
 * необходимо соблюдение двух условий:
 * <p>
 * Порядок вхождения символов должен быть сохранён. Одинаковым символам первой строки должны соответствовать одинаковые
 * символы второй строки. Разным символам —– разные. Например, если строка s = «abacaba», то ей будет равна строка t =
 * «xhxixhx», так как все вхождения «a» заменены на «x», «b» –— на «h», а «c» –— на «i». Если же первая строка s=«abc»,
 * а вторая t=«aaa», то строки уже не будут равны, так как разные буквы первой строки соответствуют одинаковым буквам
 * второй.
 * <p>
 * Формат ввода В первой строке записана строка s, во второй –— строка t. Длины обеих строк не превосходят 106. Обе
 * строки содержат хотя бы по одному символу и состоят только из маленьких латинских букв.
 * <p>
 * Строки могут быть разной длины.
 * <p>
 * Формат вывода Выведите «YES», если строки равны (согласно вышеописанным правилам), и «NO» в ином случае.
 */
public class H_Странное_сравнение {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String s = reader.readLine();
            String t = reader.readLine();

            if (s.length() != t.length()) {
                writer.write("NO");
                writer.flush();
                System.exit(0);
            }

            HashMap<Character, Character> mapping = new HashMap<>();
            HashMap<Character, Character> mapping2 = new HashMap<>();

            for (int i = 0; i < s.length(); i++) {
                Character s1 = s.charAt(i);
                Character t1 = t.charAt(i);

                Character character = mapping.get(s1);
                Character character2 = mapping2.get(t1);

                if (character == null && character2 == null) {
                    mapping.put(s1, t1);
                    mapping2.put(t1, s1);
                    continue;
                }

                if (character == null || character2 == null) {
                    writer.write("NO");
                    writer.flush();
                    System.exit(0);
                }

                if (!Objects.equals(character, t1) || !Objects.equals(character2, s1)) {
                    writer.write("NO");
                    writer.flush();
                    System.exit(0);

                }
            }

            writer.write("YES");
            writer.flush();
        }
    }
}
