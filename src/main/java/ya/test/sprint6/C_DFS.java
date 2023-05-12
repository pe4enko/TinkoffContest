package ya.test.sprint6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * Задан неориентированный граф. Обойдите с помощью DFS все вершины, достижимые из заданной вершины s, и выведите их в
 * порядке обхода, если начинать обход из s.
 * <p>
 * Формат ввода В первой строке дано количество вершин n (1 ≤ n ≤ 105) и рёбер m (0 ≤ m ≤ 105). Далее в m строках
 * описаны рёбра графа. Каждое ребро описывается номерами двух вершин u и v (1 ≤ u, v ≤ n). В последней строке дан номер
 * стартовой вершины s (1 ≤ s ≤ n). В графе нет петель и кратных рёбер.
 * <p>
 * Формат вывода Выведите вершины в порядке обхода, считая что при запуске от каждой конкретной вершины её соседи будут
 * рассматриваться в порядке возрастания (то есть если вершина 2 соединена с 1 и 3, то сначала обход пойдёт в 1, а уже
 * потом в 3).
 */
public class C_DFS {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            FromTo crutch = readFromToPair(reader);
            int n = crutch.from;
            int m = crutch.to;

            @SuppressWarnings("unchecked") TreeSet<Integer>[] list = new TreeSet[n];

            for (int i = 0; i < m; i++) {
                FromTo fromTo = readFromToPair(reader);
                TreeSet<Integer> verticesFrom = list[fromTo.from - 1];
                if (verticesFrom == null) {
                    verticesFrom = new TreeSet<>(Comparator.reverseOrder());
                    list[fromTo.from - 1] = verticesFrom;
                }
                verticesFrom.add(fromTo.to - 1);

                TreeSet<Integer> verticesTo = list[fromTo.to - 1];
                if (verticesTo == null) {
                    verticesTo = new TreeSet<>(Comparator.reverseOrder());
                    list[fromTo.to - 1] = verticesTo;
                }
                verticesTo.add(fromTo.from - 1);
            }

            int startFromVertex = readInt(reader) - 1;
            int[] color = new int[n];

            dfs(startFromVertex, list, color, writer);

            writer.flush();
        }
    }

    private static void dfs(int startFromVertex, TreeSet<Integer>[] list, int[] color, BufferedWriter writer)
            throws IOException {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(startFromVertex);

//        пока stack не пуст:
//            # Получаем из стека очередную вершину.
//            # Это может быть как новая вершина, так и уже посещённая однажды.
        Integer v;
        while ((v = stack.pollFirst()) != null) {
            int c = color[v];

            if (c == 0) { //white
//                # Красим вершину в серый. И сразу кладём её обратно в стек:
//                # это позволит алгоритму позднее вспомнить обратный путь по графу.
                color[v] = 1;
                stack.push(v);

                writer.write(v + 1 + " ");

//              Теперь добавляем в стек все непосещённые соседние вершины, вместо вызова рекурсии.
                TreeSet<Integer> vertices = list[v];
                if (vertices != null) {
                    for (Integer vertex : vertices) {
                        if (color[vertex] == 0) {
                            stack.push(vertex);
                        }
                    }
                }
            } else if (c == 1) { //gray
//                # Серую вершину мы могли получить из стека только на обратном пути.
//                # Следовательно, её следует перекрасить в чёрный.
                color[v] = 2;
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
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