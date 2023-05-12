package ya.test.sprint7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://medium.com/@vitkarpov/cracking-the-coding-interview-8-1-77d323caf8c0 Алла хочет доказать, что она умеет
 * прыгать вверх по лестнице быстрее всех. На этот раз соревнования будут проходить на специальной прыгательной
 * лестнице. С каждой её ступеньки можно прыгнуть вверх на любое расстояние от 1 до k. Число k придумывает Алла.
 * <p>
 * Гоша не хочет проиграть, поэтому просит вас посчитать количество способов допрыгать от первой ступеньки до n-й.
 * Изначально все стоят на первой ступеньке.
 * <p>
 * Формат ввода В единственной строке даны два числа — n и k (1 ≤ n ≤ 1000, 1 ≤ k ≤ n).
 * <p>
 * Формат вывода Выведите количество способов по модулю 109 + 7.
 */
public class F_Прыжки_по_лестнице {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            StringTokenizer st = new StringTokenizer(reader.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] dp = new int[n];
            dp[0] = 1;

            for (int i = 1; i < n; i++) {
                for (int j = 1; j <= k; j++) {
                    if (i - j >= 0) {
                        dp[i] = (dp[i] + dp[i - j]) % 1_000_000_007;
                    }
                }
                System.out.println(Arrays.toString(dp));
            }

            writer.write(String.valueOf(dp[n - 1]));
            writer.flush();
        }
    }
}
