package ya.test.sprint1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

/**
 * Вася просил Аллу помочь решить задачу. На этот раз по информатике.
 * <p>
 * Для неотрицательного целого числа X списочная форма –— это массив его цифр слева направо. К примеру, для 1231
 * списочная форма будет [1,2,3,1]. На вход подается количество цифр числа Х, списочная форма неотрицательного числа Х и
 * неотрицательное число K. Числа К и Х не превосходят 10000.
 * <p>
 * Нужно вернуть списочную форму числа X + K.
 * <p>
 * Формат ввода В первой строке — длина списочной формы числа X. На следующей строке — сама списочная форма с цифрами
 * записанными через пробел.
 * <p>
 * В последней строке записано число K, 0 ≤ K ≤ 10000.
 * <p>
 * Формат вывода Выведите списочную форму числа X+K.
 */
public class K_Списочная_форма {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            int bigInt = readBigInt(reader);
            int secondInt = readInt(reader);

            int result = bigInt + secondInt;

            StringBuilder sb = new StringBuilder();

            StringCharacterIterator iter = new StringCharacterIterator(String.valueOf(result));
            for (char c = iter.first(); c != CharacterIterator.DONE; c = iter.next()) {
                sb.append(c).append(" ");
            }

            sb.deleteCharAt(sb.length() - 1);

            writer.write(sb.toString());
            writer.flush();
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int readBigInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(String.join("", reader.readLine().split(" ")));

    }
}
