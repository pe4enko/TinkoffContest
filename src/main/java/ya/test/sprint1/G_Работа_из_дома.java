package ya.test.sprint1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Вася реализовал функцию, которая переводит целое число из десятичной системы в двоичную. Но, кажется, она получилась
 * не очень оптимальной.
 * <p>
 * Попробуйте написать более эффективную программу. Не используйте встроенные средства языка по переводу чисел в
 * бинарное представление.
 */
public class G_Работа_из_дома {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);

            StringBuilder sb = new StringBuilder();

            while (n != 0) {
                sb.append(n % 2);
                n = n / 2;
            }

            writer.write(sb.reverse().toString());
            writer.flush();
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
