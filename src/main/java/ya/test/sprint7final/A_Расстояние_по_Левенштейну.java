package ya.test.sprint7final;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *
 */
public class A_Расстояние_по_Левенштейну {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String a = reader.readLine();
            String b = reader.readLine();

            int al = a.length() + 1;
            int bl = b.length() + 1;
            int[][] dp = new int[al][bl];

            for (int r = 1; r < al; r++) {
                dp[r][0] = r;
            }

            for (int c = 0; c < bl; c++) {
                dp[0][c] = c;
            }

            for (int r = 1; r < al; r++) {
                for (int c = 1; c < bl; c++) {
                    if (a.charAt(r - 1) == b.charAt(c - 1)) {
                        dp[r][c] = dp[r - 1][c - 1];
                    } else {
                        int delete = dp[r - 1][c] + 1;
                        int insert = dp[r][c - 1] + 1;
                        int replace = dp[r - 1][c - 1] + 1;

                        dp[r][c] = Math.min(Math.min(delete, insert), replace);
                    }
                }
            }

            writer.write(String.valueOf(dp[al - 1][bl - 1]));
            writer.flush();
        }

    }
}
