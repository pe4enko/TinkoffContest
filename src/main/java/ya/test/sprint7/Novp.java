package ya.test.sprint7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 */
public class Novp {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int[] A = {1, 3, 1, 2, 8, 9, 1};
            int[] B = {3, 8, 9, 1, 3, 1, 2, 1};

            int[][] dp = new int[A.length + 1][B.length + 1];

            for (int i = 1; i <= A.length; i++) {
                for (int j = 1; j <= B.length; j++) {
                    if (A[i - 1] == B[j - 1]) {
                        int max_len = 0;

                        for (int i_prev = 1; i_prev < i; i_prev++) {
                            if (A[i_prev - 1] >= A[i - 1]) { // Проверка что последовательность возрастает!!!
                                continue;
                            }
                            for (int j_prev = 1; j_prev < j; j_prev++) {
                                max_len = Math.max(max_len, dp[i_prev][j_prev]);
                            }
                        }
                        dp[i][j] = max_len + 1;
                    }
//                    for (int k = 0; k < dp.length; k++) {
//                        System.out.println(Arrays.toString(dp[k]));
//                    }
                    System.out.println("");
                }
            }

            int answer = 0;
            for (int i = 1; i <= A.length; i++) {
                for (int j = 1; j <= B.length; j++) {
                    answer = Math.max(answer, dp[i][j]);
                }
            }

            writer.write(answer + "\n");
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
