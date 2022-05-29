package ya.test.sprint1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Тимофей готовит доклад ко дню открытых дверей кафедры Теории чисел. Он собирается рассказать про Основную теорему
 * арифметики. В соответствии с этой теоремой, любое число раскладывается на произведение простых множителей
 * единственным образом –— с точностью до их перестановки.
 * <p>
 * Например, число 8 можно представить как 2 × 2 × 2.
 * <p>
 * Число 50 –— как 2 × 5 × 5 (или 5 × 5 × 2, или 5 × 2 × 5). Три варианта отличаются лишь порядком следования
 * множителей.
 * <p>
 * Разложение числа на простые множители называется факторизацией числа.
 * <p>
 * Факторизацию в уме делать сложно, поэтому помогите Тимофею написать для этого программу.
 * <p>
 * Формат ввода В единственной строке дано число n (2 ≤ n ≤ 109), которое нужно факторизовать.
 * <p>
 * Формат вывода Выведите в порядке неубывания простые множители, на которые раскладывается число n.
 */
public class J_Факторизация {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            StringBuilder sb = new StringBuilder();

            int divTo = 2;
            int n = readInt(reader);

            do {
                boolean isWhithoutRemainder = 0 == (n % divTo);

                if (isWhithoutRemainder) {
                    sb.append(divTo);
                    n = n / divTo;
                    if (n > 1) {
                        sb.append(" ");
                    }
                } else {
                    if (divTo == 2) {
                        divTo++;
                    } else {
                        divTo += 2;
                    }
                }
            } while (n > 1);

            writer.write(sb.toString());
            writer.flush();
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
