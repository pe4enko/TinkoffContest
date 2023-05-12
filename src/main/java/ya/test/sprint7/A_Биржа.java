package ya.test.sprint7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * https://russianblogs.com/article/20561283301/
 *
 * Рита хочет попробовать поиграть на бирже. Но для начала она решила потренироваться на исторических данных.
 * <p>
 * Даны стоимости акций в каждый из n дней. В течение дня цена акции не меняется. Акции можно покупать и продавать, но
 * только по одной штуке в день. В один день нельзя совершать более одной операции (покупки или продажи). Также на руках
 * не может быть более одной акции в каждый момент времени.
 * <p>
 * Помогите Рите выяснить, какую максимальную прибыль она могла бы получить.
 * <p>
 * Пояснения к примерам
 * <p>
 * Пример 1 Рита может купить акцию во 2-й день за 1 франк.
 * <p>
 * Затем она продаст её на 3-й день за 5 франков.
 * <p>
 * В 4-й день она снова купит акцию за 3 франка.
 * <p>
 * На 5-й день Рита продаст эту акцию за 6 франков.
 * <p>
 * Прибыль составила (5 - 1) + (6 - 3) = 7 франков.
 * <p>
 * Пример 2 Рите выгодно купить акцию в самый первый день и продать в последний.
 * <p>
 * Пример 3 Рита покупает акции в дни с номерами 1, 3 и 5. Продаёт в дни 2, 4 и 6. Итоговая прибыль составит (12 - 1) +
 * (16 - 12) + (8 - 1) = 22. Такой же результат можно получить в виде: 22 = (16 - 1) + (8 - 1), если покупать акции в
 * дни 1 и 5, а продавать в дни 4 и 6.
 * <p>
 * Формат ввода В первой строке записано количество дней n —– целое число в диапазоне от 0 до 10 000.
 * <p>
 * Во второй строке через пробел записано n целых чисел в диапазоне от 0 до 1000 –— цены акций.
 * <p>
 * Формат вывода Выведите число, равное максимально возможной прибыли за эти дни.
 */
public class A_Биржа {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            int[] prices = readIntArrayWithSize(n, reader);

            Integer maxProfit = 0;
            for (int i = 1; i < n; i++) {
                Integer buy = prices[i - 1];
                Integer sold = prices[i];

                if (buy < sold) {
                    maxProfit += sold - buy;
                }
            }

            writer.write(String.valueOf(maxProfit));
            writer.flush();
        }

    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readIntArrayWithSize(int size, BufferedReader reader) throws IOException {

        final int[] result = new int[size];
        int i = 0;

        StringTokenizer st = new StringTokenizer(reader.readLine());
        while (st.hasMoreTokens()) {
            result[i] = Integer.parseInt(st.nextToken());
            i++;
        }

        return result;
    }
}
