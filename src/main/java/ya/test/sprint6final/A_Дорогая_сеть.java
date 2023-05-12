package ya.test.sprint6final;

import static java.util.Comparator.comparingInt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * https://contest.yandex.ru/contest/25070/run-report/69694903/ -- ПРИНЦИП РАБОТЫ -- Для реализации задачи был
 * использован алгоритм Прима для поиска максимального остовного дерева. На первом этапе мы выбираем любую вершину из
 * графа. Все ребра исходящие из этой вершины добавляем в список потенциальных ребер остова. Из списка потенциальных
 * ребер выбираем ребро с максимальным весом и если оно указывает на вершину еще не обработанную тогда добавляем это
 * ребро в итоговое остовное дерево. И переходим к обработке вершины на которую указывает ребро аналогичным образом.
 * Алгорит заканчивает работу когда в списке потенциальных ребер не останется ребер, т.е. будут обработаны все. После
 * чего мы можем проверить имееют ли еще вершины, которые мы не посетили, т.е. те которые не доступны из вершины,
 * выбранной нами для старта. Если такие имеются, то это значит что граф не связанный и мы не можем построить
 * максимальное остовное дерево - выводим ошибку.
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ -- Так как на этапе выбора по какому ребру продолжить свой путь мы выбираем ребро с
 * максимальным весом из всех возможных путей, то очевидно, что в итоге мы обойдем дерево по самому длинному пути.
 * Алгоритм корректен по построению.
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * 1. Заполнение множества не обработанных вершин O(|V|)
 * 2. В процессе обработки мы все эти
 * вершины удалим из этого списка O(|V|)
 * 3. Каждое ребро необходимо добавить в список потенциальных ребер, т.к.
 * используется приоритетная очередь, то добавление одного ребра занимает O(logN), всего |E| ребер, т.е. добавление всех
 * ребер в очередь займет O(|E| * log|E|)
 * 4. Предварительно необходимо проверить, а не добавлено ли уже это ребро O(|E|)
 * 5. Обработка завершается тогда, когда мы извлечем все ребра из потенциально добавленных, сложность удаления всех
 * ребер такая же как сложность добавления   O(|E| * log|E|).
 * ИТОГО: O(2 * O(|V|) + 2 * O(|E| * log|E|) + O(|E|) = O(|V| + |E| * log|E|)
 * Для полного графа следует считать, что O(E) = O(V^2)
 * Следовательно итоговую сложность можно записать в виде O(|V| + |E| * log|V^2|).
 * Мы знаем, что log(V^2) = 2*log(V) следовательно итоговая слоджность будет O(|V| + |E| * 2* log|V|)
 * Отбрасываем части которые на бесконечности будут иметь наименьший эффект и получи
 * O(|E| * log|V|)
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ -- Граф хранится в виде списка смежности, пространственная сложность для такого
 * хранения равна O(|V| + |E|) Во время обработки дополнительно выделяется память для хранения итогового MST -> O(|E|).
 * Для хранения множества вершины, ещё не добавленных в остов -> O(|V|) Для хранения потенциальных ребер -> O(|E|).
 * ИТОГ: пространственная сложность алгооритма равноа O(|V| + |E|)
 */
public class A_Дорогая_сеть {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            Edge nm = readPair(reader);
            int n = nm.from;
            int m = nm.to;

            Map<Edge, Edge>[] list = initAdjacencyList(n, m, reader);

            try {
                Set<Edge> mst = findMst(list);

                int sum = mst.stream()
                        .map(e -> e.weight)
                        .mapToInt(Integer::intValue)
                        .sum();

                writer.write(String.valueOf(sum));
            } catch (Exception e) {
                writer.write(e.getMessage());
            }

            writer.flush();
        }
    }

    private static Map<Edge, Edge>[] initAdjacencyList(
            int verticesCount,
            int edgesToRead,
            BufferedReader reader) throws IOException {

        @SuppressWarnings("unchecked") Map<Edge, Edge>[] list = new HashMap[verticesCount];

        for (int i = 0; i < edgesToRead; i++) {
            Edge fromToWeight = readTriple(reader);

            fillAdjacencyList(list, fromToWeight.from, fromToWeight.to, fromToWeight.weight);
            fillAdjacencyList(list, fromToWeight.to, fromToWeight.from, fromToWeight.weight);
        }

        return list;
    }

    private static void fillAdjacencyList(Map<Edge, Edge>[] list, int fromVertex, int toVertex, int weight) {
        Map<Edge, Edge> edges = list[fromVertex - 1];
        if (edges == null) {
            edges = new HashMap<>();
            list[fromVertex - 1] = edges;
        }

        Edge newEdge = new Edge(fromVertex - 1, toVertex - 1, weight);
        Edge vertex = edges.get(newEdge);
        if (vertex != null) {
            if (newEdge.weight > vertex.weight) {
                edges.put(newEdge, newEdge);
            }
        } else {
            edges.put(newEdge, newEdge);
        }
    }

    private static Set<Edge> findMst(Map<Edge, Edge>[] list) {

        /* Рёбра, составляющие MST. */
        Set<Edge> maximumSpanningTree = new HashSet<>();

        /* Множество вершины, ещё не добавленных в остов. */
        Set<Integer> notProcessedVertex = new HashSet<>();

        /* Массив рёбер, исходящих из остовного дерева. */
        PriorityQueue<Edge> potentiallyAddedEdges = new PriorityQueue<>(comparingInt(Edge::getWeight).reversed());

        //Инициализируем множество еще не добавленных вершин в остановное дерево
        for (int i = 0; i < list.length; i++) {
            notProcessedVertex.add(i);
        }

        processVertex(0, notProcessedVertex, potentiallyAddedEdges, list);

        Edge e;
        while ((e = extractLongestEdge(potentiallyAddedEdges)) != null) {
            if (notProcessedVertex.contains(e.to)) {
                maximumSpanningTree.add(e);
                processVertex(e.to, notProcessedVertex, potentiallyAddedEdges, list);
            }
        }

        //Если мы обработали все ребра, но остались какие-то не посещенные вершины, значит из вершины
        //с которой мы начали обход нельзя попасть в какие-то из вершин, т.е. дерево не связанное.
        if (!notProcessedVertex.isEmpty()) {
            throw new RuntimeException("Oops! I did it again");
        }

        return maximumSpanningTree;
    }

    private static void processVertex(int vertex,
            Set<Integer> notProcessedVertex,
            Collection<Edge> potentiallyAddedEdges,
            Map<Edge, Edge>[] list) {

        notProcessedVertex.remove(vertex);

        // Добавляем в потенциальные ребра, все рёбра, которые инцидентны v, но их конец ещё не в остове.
        Map<Edge, Edge> edges = list[vertex];

        if (edges == null) {
            return;
        }

        for (Edge e : edges.values()) {
            if (notProcessedVertex.contains(e.to)) {
                potentiallyAddedEdges.add(new Edge(vertex, e.to, e.weight));
            }
        }
    }

    /**
     * Находит среди понетциально добавляемых ребер, то которое имеет максимальный вес. Удаляет найденное ребро из
     * потенцильно добавляемых.
     */
    private static Edge extractLongestEdge(PriorityQueue<Edge> potentiallyAddedEdges) {
        return potentiallyAddedEdges.poll();
    }

    private static Edge readPair(BufferedReader reader) throws IOException {
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        return new Edge(from, to, 0);
    }

    private static Edge readTriple(BufferedReader reader) throws IOException {
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        int weight = Integer.parseInt(st.nextToken());

        return new Edge(from, to, weight);
    }

    private static class Edge {

        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Edge edge = (Edge) o;
            return from == edge.from && to == edge.to;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }
}