package ya.test.sprint6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.OptionalInt;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Задан неориентированный граф. Обойдите поиском в ширину все вершины, достижимые из заданной вершины s, и выведите их
 * в порядке обхода, если начинать обход из s.
 * <p>
 * Формат ввода В первой строке дано количество вершин n (1 ≤ n ≤ 105) и рёбер m (0 ≤ m ≤ 105). Далее в m строках
 * описаны рёбра графа. Каждое ребро описывается номерами двух вершин u и v (1 ≤ u, v ≤ n). В последней строке дан номер
 * стартовой вершины s (1 ≤ s ≤ n).
 * <p>
 * Гарантируется, что в графе нет петель и кратных рёбер.
 * <p>
 * Формат вывода Выведите вершины в порядке обхода, считая что при запуске от каждой конкретной вершины её соседи будут
 * рассматриваться в порядке возрастания (то есть если вершина 2 соединена с 1 и 3, то сначала обход пойдёт в 1, а уже
 * потом в 3).
 */
public class G_Максимальное_расстояние {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            FromTo crutch = readFromToPair(reader);
            int n = crutch.from;
            int m = crutch.to;

            @SuppressWarnings("unchecked") HashSet<Integer>[] list = new HashSet[n];

            for (int i = 0; i < m; i++) {
                FromTo fromTo = readFromToPair(reader);
                HashSet<Integer> verticesFrom = list[fromTo.from - 1];
                if (verticesFrom == null) {
                    verticesFrom = new HashSet<>();
                    list[fromTo.from - 1] = verticesFrom;
                }
                verticesFrom.add(fromTo.to - 1);

                HashSet<Integer> verticesTo = list[fromTo.to - 1];
                if (verticesTo == null) {
                    verticesTo = new HashSet<>();
                    list[fromTo.to - 1] = verticesTo;
                }
                verticesTo.add(fromTo.from - 1);
            }

            int startFromVertex = readInt(reader) - 1;
            int[] color = new int[n];
            int[] previous = new int[n];
            int[] distance = new int[n];

            bfs(startFromVertex, list, color, distance, previous, writer);

            OptionalInt max = Arrays.stream(distance).max();
            if (max.isPresent()) {
                writer.write(String.valueOf(max.getAsInt()));
            } else {
                writer.write("0");
            }

            writer.flush();
        }
    }

    private static void bfs(
            int startFromVertex,
            HashSet<Integer>[] list,
            int[] color,
            int[] distance,
            int[] previous,
            BufferedWriter writer) throws IOException {

        Queue<Integer> planned = new LinkedList<>();
        planned.add(startFromVertex);

        color[startFromVertex] = 1;
        distance[startFromVertex] = 0;

//    пока очередь planned не пуста:
        Integer u;
        while ((u = planned.poll()) != null) { //Возьмём вершину из очереди.
            //для каждого ребра (u,v), исходящего из u:
            HashSet<Integer> vertices = list[u];
            if (vertices != null) {
                for (Integer vertex : vertices) {
                    if (color[vertex] == 0) { //Серые и чёрные вершины уже либо в очереди, либо обработаны.
                        distance[vertex] = distance[u] + 1;
                        previous[vertex] = u;
                        color[vertex] = 1;
                        planned.add(vertex); //Запланируем посещение вершины.
                    }
                }
            }

            color[u] = 2; //Теперь вершина считается обработанной.
//            writer.write(u + 1 + " ");
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