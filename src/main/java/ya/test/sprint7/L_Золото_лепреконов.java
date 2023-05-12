package ya.test.sprint7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * Лепреконы в данной задаче появились по соображениям общей морали, так как грабить банки — нехорошо.
 * <p>
 * Вам удалось заключить неплохую сделку с лепреконами, поэтому они пустили вас в своё хранилище золотых слитков. Все
 * слитки имеют единую пробу, то есть стоимость 1 грамма золота в двух разных слитках одинакова. В хранилище есть n
 * слитков, вес i-го слитка равен wi кг. У вас есть рюкзак, вместимость которого M килограмм.
 * <p>
 * Выясните максимальную суммарную массу золотых слитков, которую вы сможете унести.
 * <p>
 * Формат ввода В первой строке дано число слитков —– натуральное число n (1 ≤ n ≤ 1000) и вместимость рюкзака –— целое
 * число M (0 ≤ M ≤ 104). Во второй строке записано n натуральных чисел wi (1 ≤ wi ≤ 104) -— массы слитков.
 * <p>
 * Формат вывода Выведите единственное число — максимальную массу, которую можно забрать с собой.
 */
public class L_Золото_лепреконов {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            StringTokenizer st = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken()) + 1;

            int[] weights = readIntArrayWithSize(n, reader);

            int[][] dp = new int[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int wi = weights[i] > j ? 0: weights[i] ;
                    int current = wi + ((i - 1) < 0 ? 0 : (j - wi) <= 0 ? 0 : (dp[i - 1][j - wi]));
                    int up = i - 1 < 0 ? 0 : dp[i - 1][j];

                    dp[i][j] = Math.max(current, up);
                }
            }

            writer.write(String.valueOf(dp[n - 1][m - 1]));
            writer.flush();
        }

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
