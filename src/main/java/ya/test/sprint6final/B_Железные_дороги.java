package ya.test.sprint6final;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *
 */
public class B_Железные_дороги {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = Integer.parseInt(reader.readLine());

            boolean[][] matrixB = new boolean[n][n];
            boolean[][] matrixR = new boolean[n][n];

            for (int i = 0; i < n - 1; i++) {
                String s = reader.readLine();
                for (int j = 0; j < s.length(); j++) {
                    if (s.charAt(j) == 'B') {
                        matrixB[i][i + j + 1] = true;
                    } else {
                        matrixR[i][i + j + 1] = true;
                    }
                }
            }

            process(matrixB);
            process(matrixR);

            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    if (matrixB[i][j] && matrixR[i][j]) {
                        writer.write("NO");
                        writer.flush();
                        System.exit(0);
                    }
                }
            }

            writer.write("YES");
            writer.flush();
        }
    }

    private static void process(boolean[][] matrix) {
        int n = matrix.length;

        for (int a = 0; a < n; a++) {
            for (int b = a; b < n; b++) {
                //Если A и B не соединены
                if (!matrix[a][b]) {
                    continue;
                }
                for (int c = b; c < n; c++) {
                    matrix[a][c] = matrix[a][c] || (matrix[b][c]);
                }
            }
        }
    }
}