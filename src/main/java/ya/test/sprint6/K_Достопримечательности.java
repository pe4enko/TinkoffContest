package ya.test.sprint6;

import static java.lang.Integer.MAX_VALUE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * todo: Для решения этой задачи необходимо использовать Алгоритм Флойда
 * Вы приехали на архипелаг Алгосы (наконец-то!). Здесь есть n достопримечательностей. Ваша лодка может высадить вас у
 * одной из них, забрать у какой-то другой, возможно, той же самой достопримечательности и увезти на материк.
 * <p>
 * Чтобы более тщательно спланировать свой маршрут, вы хотите узнать расстояния между каждой парой
 * достопримечательностей Алгосов. Некоторые из них соединены мостами, по которым вы можете передвигаться в любую
 * сторону. Всего мостов m.
 * <p>
 * Есть вероятность, что карта архипелага устроена так, что нельзя добраться от какой-то одной достопримечательности до
 * другой без использования лодки.
 * <p>
 * Найдите кратчайшие расстояния между всеми парами достопримечательностей.
 * <p>
 * Формат ввода В первой строке даны числа n (1 ≤ n ≤ 100) и m (0 ≤ m ≤ n (n-1)/2). В следующих m строках описаны мосты.
 * Каждый мост задаётся номерами двух достопримечательностей 1 ≤ u, v ≤ n и длиной дороги 1 ≤ L ≤ 103.
 * <p>
 * Формат вывода Выведите матрицу n × n кратчайших расстояний. На пересечении i-й строки и j-го столбца должно стоять
 * расстояние от i-й до j-й достопримечательности. Если между какой-то парой нет пути, то в соответствующей клетке
 * поставьте -1.
 */
public class K_Достопримечательности {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            FromTo crutch = readFromToPair(reader);
            int n = crutch.from;
            int m = crutch.to;

            @SuppressWarnings("unchecked") HashSet<Vertex>[] list = new HashSet[n];

            for (int i = 0; i < m; i++) {
                FromTo fromTo = readTriple(reader);
                HashSet<Vertex> verticesFrom = list[fromTo.from - 1];
                if (verticesFrom == null) {
                    verticesFrom = new HashSet<>();
                    list[fromTo.from - 1] = verticesFrom;
                }
                verticesFrom.add(new Vertex(fromTo.to - 1, fromTo.weight));

                HashSet<Vertex> verticesTo = list[fromTo.to - 1];
                if (verticesTo == null) {
                    verticesTo = new HashSet<>();
                    list[fromTo.to - 1] = verticesTo;
                }
                verticesTo.add(new Vertex(fromTo.from - 1, fromTo.weight));
            }

            for (int i = 0; i < n; i++) {
                int[] distances = dijkstra(i, list);

                String s = Arrays.stream(distances)
                        .map(operand -> {
                            if (operand == MAX_VALUE) {
                                return -1;
                            } else {
                                return operand;
                            }
                        })
                        .mapToObj(Objects::toString)
                        .collect(Collectors.joining(" "));
                writer.write(s + "\n");
            }

            writer.flush();
        }
    }

    private static int[] dijkstra(int startFromVertex, HashSet<Vertex>[] list) {
        boolean[] visited = new boolean[list.length];
        int[] distance = new int[list.length];

        // init
        Arrays.fill(distance, MAX_VALUE);
        distance[startFromVertex] = 0; //Расстояние от вершины до самой себя 0.

        Integer from = startFromVertex;

        while (from != null) {
            HashSet<Vertex> fromVertex = list[from];
            visited[from] = true;

            //Для каждой вершины на которую есть ссылка из текущей обновляем минимальную дистанцию.
            if (fromVertex != null) {
                for (Vertex vertex : fromVertex) {
                    int newDist = distance[from] + vertex.weight;
                    if (distance[vertex.to] > newDist) {
                        distance[vertex.to] = newDist;
                    }
                }
            }

            //Далее найдем следующую вершину с минимальным расстоянием, которую будем рассматирвать

            from = getMinDistanceNotVisitedVertex(visited, distance);
        }

        return distance;
    }

    private static Integer getMinDistanceNotVisitedVertex(boolean[] visited, int[] distance) {
//    # Находим ещё непосещённую вершину с минимальным расстоянием от s.
        int currentMinimumDistance = MAX_VALUE;
        Integer currentMinimumVertex = null;

        for (int i = 0; i < visited.length; i++) {
            boolean vis = visited[i];
            int dist = distance[i];
            if (!vis && dist < currentMinimumDistance) {
                currentMinimumDistance = dist;
                currentMinimumVertex = i;
            }
        }

        return currentMinimumVertex;
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

    private static class Vertex {

        int to;
        int weight;

        public Vertex(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Vertex vertex = (Vertex) o;
            return to == vertex.to;
        }

        @Override
        public int hashCode() {
            return Objects.hash(to);
        }
    }
}