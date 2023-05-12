package ya.test.sprint6final;

import static ya.test.sprint6final.Colors.BLACK;
import static ya.test.sprint6final.Colors.GRAY;
import static ya.test.sprint6final.Colors.WHITE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * -- ПРИНЦИП РАБОТЫ --
 * Данная задача является задачей проверки имеются ли циклы в графе.
 * Для обхода графа используется алгоритм обхода в глубину. Данный алгоритм использует 3-х цветную раскраску
 * вершин для обозначения какие из них уже обработаны, какие еще обрабатываются, и обработка каких еще не начата.
 * Текущие обрабатываемые вершины обозначаются серым цветом. Таким образом если во время всех вершин при спуске
 * нам встретилась серая вершина, то это означает, что в графе имеется цикл.
 * Так как граф направленный и может быть не связанным то нам необходимо запускать обход в глубину для всех
 * вершин которые еще не покрашены.
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Алгоритм DFS перебирает все связанные вершины, при начале обработке вершины он помечает вершину как Серую,
 * при окончании обработки как Черную. Очевидно, что если при погружении встречается уже Серая вершина, то это
 * означает, что мы имеем цикл. Алгоритм корректен по построению.
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Для хранения графа используется список смежности.
 * Поскольку алгоритм обрабатывает все вершины, ему придётся пройтись по всем спискам смежности.
 * Это эквивалентно тому, чтобы пройти по каждому ребру по одному разу, что займёт O(|E|).
 * Получим, что итоговая сложность алгоритма O(|V| + |E|)
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Граф хранится в виде списка смежности, пространственная сложность для такого хранения равна O(|V| + |E|)
 * Во время обработки используется массив цветов пространственная сложность которого равна O(|V|)
 * Стек с вершинами максимум пространственная сложность может быть если все вершины графа идут одна за другой
 * и в таком случае пространственная сложность будет равна O(|V|)
 * ИТОГ: пространственная сложность алгооритма равноа O(|V| + |E|)
 */
public class B_Железные_дороги_1 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = Integer.parseInt(reader.readLine());

            List<Integer>[] list = initAdjacencyList(reader, n);

            Colors[] color = new Colors[list.length];
            Arrays.fill(color, WHITE);

            for (int v = 0; v < n; v++) {
                if (color[v] == WHITE) {
                    dfs(v, color, list, writer);
                    // dfsWithRecursion(v, list, color, writer);
                }
            }

            writer.write("YES");
            writer.flush();
        }
    }

    @NotNull
    private static List<Integer>[] initAdjacencyList(BufferedReader reader, int n) throws IOException {
        @SuppressWarnings("unchecked") List<Integer>[] list = new ArrayList[n];

        for (int i = 0; i < n - 1; i++) {
            String s = reader.readLine();
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == 'R') {
                    fillAdjacencyList(list, i, i + j + 1);
                } else {
                    fillAdjacencyList(list, i + j + 1, i);
                }
            }
        }
        return list;
    }

    private static void fillAdjacencyList(List<Integer>[] list, int from, int to) {
        List<Integer> vertices = list[from];
        if (vertices == null) {
            vertices = new ArrayList<>();
            list[from] = vertices;
        }
        vertices.add(to);
    }

    private static void dfsWithRecursion(int v, Iterable<Integer>[] list, Colors[] color, BufferedWriter writer)
            throws IOException {
        color[v] = GRAY;

        Iterable<Integer> vertices = list[v];
        if (vertices != null) {
            for (Integer vertex : vertices) {
                if (color[vertex] == WHITE) {
                    dfsWithRecursion(vertex, list, color, writer);
                } else if (color[vertex] == GRAY) {
                    writer.write("NO");
                    writer.flush();
                    System.exit(0);
                }
            }
        }

        color[v] = BLACK;
    }

    private static void dfs(int startFromVertex, Colors[] color, Iterable<Integer>[] list, BufferedWriter writer)
            throws IOException {

        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(startFromVertex);

        // пока stack не пуст:
        //  Получаем из стека очередную вершину.
        //  Это может быть как новая вершина, так и уже посещённая однажды.
        Integer v;
        while ((v = stack.pollFirst()) != null) {
            Colors c = color[v];

            if (c == WHITE) {
                // Красим вершину в серый. И сразу кладём её обратно в стек:
                // это позволит алгоритму позднее вспомнить обратный путь по графу.
                color[v] = GRAY;
                stack.push(v);

                // Теперь добавляем в стек все непосещённые соседние вершины, вместо вызова рекурсии.
                Iterable<Integer> vertices = list[v];
                if (vertices != null) {
                    for (Integer vertex : vertices) {
                        if (color[vertex] == WHITE) {
                            stack.push(vertex);
                        } else if (color[vertex] == GRAY) {
                            writer.write("NO");
                            writer.flush();
                            System.exit(0);
                        }
                    }
                }
            } else if (c == GRAY) {
                // Серую вершину мы могли получить из стека только на обратном пути.
                // Следовательно, её следует перекрасить в чёрный.
                color[v] = BLACK; //black
            }
        }
    }
}

enum Colors {
    WHITE,
    GRAY,
    BLACK
}