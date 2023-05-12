package ya.test.sprint6;

import static java.lang.Integer.MAX_VALUE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * Для решения этой задачи необходимо использовать Алгоритм Флойда https://www.youtube.com/watch?v=DmrFwLcDl9c&t=30s
 */
public class K_Достопримечательности_Floyd {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            FromTo crutch = readFromToPair(reader);
            int n = crutch.from;
            int m = crutch.to;

            int[][] matrix = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = MAX_VALUE;
                }
            }

            for (int i = 0; i < m; i++) {
                FromTo fromTo = readTriple(reader);
                matrix[fromTo.from - 1][fromTo.to - 1] = fromTo.weight;
                matrix[fromTo.to - 1][fromTo.from - 1] = fromTo.weight;
            }

            for (int i = 0; i < n; i++) {
                matrix[i][i] = 0;
            }

            //Если мы можем попасть из А в С, используя Б как промежуточную ноду
            //и этот путь короче чем уже известный нам путь напрямую в С,
            //то мы нашли более короткий путь и мы его записываем.
            for (int a = 0; a < n; a++) { //A
                for (int b = 0; b < n; b++) { //B
                    //Если A и B не соединены
                    if (matrix[a][b] == MAX_VALUE) {
                        continue;
                    }
                    for (int c = 0; c < n; c++) { //C
                        //Проверка чтобы не было переполнения
                        if (matrix[a][b] != MAX_VALUE && matrix[b][c] != MAX_VALUE) {
                            matrix[a][c] = Math.min(matrix[a][c], matrix[a][b] + matrix[b][c]);
                        }
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    writer.write((matrix[i][j] == MAX_VALUE ? -1 : matrix[i][j]) + " ");
                }
                writer.write("\n");
            }

            writer.flush();
        }
    }

    private static FromTo readFromToPair(BufferedReader reader) throws IOException {
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        return new FromTo(from, to, 0);
    }

    private static FromTo readTriple(BufferedReader reader) throws IOException {
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        int weight = Integer.parseInt(st.nextToken());

        return new FromTo(from, to, weight);
    }

    private static class FromTo {

        int from;
        int to;

        int weight;

        public FromTo(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}