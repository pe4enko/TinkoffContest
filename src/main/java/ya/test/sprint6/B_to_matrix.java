package ya.test.sprint6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * B. Перевести список ребер в матрицу смежности Алла успешно справилась с предыдущим заданием, и теперь ей дали новое.
 * На этот раз список рёбер ориентированного графа надо переводить в матрицу смежности. Конечно же, Алла попросила вас
 * помочь написать программу для этого.
 * <p>
 * Формат ввода В первой строке дано число вершин n (1 ≤ n ≤ 100) и число рёбер m (1 ≤ m ≤ n(n-1)). В следующих m
 * строках заданы ребра в виде пар вершин (u,v), если ребро ведет от u к v.
 * <p>
 * Формат вывода Выведите матрицу смежности n на n. На пересечении i-й строки и j-го столбца стоит единица, если есть
 * ребро, ведущее из i в j.
 */
public class B_to_matrix {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            FromTo crutch = readFromToPair(reader);
            int n = crutch.from;
            int m = crutch.to;

            int[][] matrix = new int[n][n];

            for (int i = 0; i < m; i++) {
                FromTo fromTo = readFromToPair(reader);
                matrix[fromTo.from - 1][fromTo.to - 1] = 1;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    writer.write(String.valueOf(matrix[i][j]) + " ");
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

        return new FromTo(from, to);
    }

    private static class FromTo {

        int from;
        int to;

        public FromTo(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }
}
