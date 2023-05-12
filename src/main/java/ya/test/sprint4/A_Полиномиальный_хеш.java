package ya.test.sprint4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Алле очень понравился алгоритм вычисления полиномиального хеша. Помогите ей написать функцию, вычисляющую хеш строки
 * s. В данной задаче необходимо использовать в качестве значений отдельных символов их коды в таблице ASCII.
 * <p>
 * Полиномиальный хеш считается по формуле:
 * <p>
 * <p>
 * Формат ввода В первой строке дано число a (1 ≤ a ≤ 1000) –— основание, по которому считается хеш.
 * <p>
 * Во второй строке дано число m (1 ≤ m ≤ 109) –— модуль.
 * <p>
 * В третьей строке дана строка s (0 ≤ |s| ≤ 106), состоящая из больших и маленьких латинских букв.
 * <p>
 * Формат вывода Выведите целое неотрицательное число –— хеш заданной строки.
 */
public class A_Полиномиальный_хеш {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int a = readInt(reader);
            int m = readInt(reader);
            String s = reader.readLine();

            long hash = 0;
            long stepen = 1;
            int n = s.length();

//            (a * b) mod c = ((a mod c) * (b mod c)) mod c

            for (int i = n - 1; i >= 0; i--) {
                char c = s.charAt(i);
                hash = (hash + ((c % m) * stepen) % m) % m;
                stepen = (stepen * (a % m)) % m;
            }

            writer.write(String.valueOf(hash));

            writer.flush();
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
