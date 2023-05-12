package ya.test.sprint6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Дан ациклический ориентированный граф (так называемый DAG, directed acyclic graph). Найдите его топологическую
 * сортировку, то есть выведите его вершины в таком порядке, что все рёбра графа идут слева направо. У графа может быть
 * несколько подходящих перестановок вершин. Вам надо найти любую топологическую сортировку.
 * <p>
 * Формат ввода В первой строке даны два числа – количество вершин n (1 ≤ n ≤ 105) и количество рёбер m (0 ≤ m ≤ 105). В
 * каждой из следующих m строк описаны рёбра по одному на строке. Каждое ребро представлено парой вершин (from, to), 1≤
 * from, to ≤ n, соответственно номерами вершин начала и конца.
 * <p>
 * Формат вывода Выведите номера вершин в требуемом порядке.
 */
public class J_Топологическая_сортировка {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            FromTo crutch = readFromToPair(reader);
            int n = crutch.from;
            int m = crutch.to;

            @SuppressWarnings("unchecked") HashSet<Integer>[] list = new HashSet[n];
            @SuppressWarnings("unchecked") HashSet<Integer>[] listTo = new HashSet[n];

            for (int i = 0; i < m; i++) {
                FromTo fromTo = readFromToPair(reader);
                HashSet<Integer> verticesFrom = list[fromTo.from - 1];
                if (verticesFrom == null) {
                    verticesFrom = new HashSet<>();
                    list[fromTo.from - 1] = verticesFrom;
                }
                verticesFrom.add(fromTo.to - 1);

                HashSet<Integer> verticesTo = listTo[fromTo.to - 1];
                if (verticesTo == null) {
                    verticesTo = new HashSet<>();
                    listTo[fromTo.to - 1] = verticesTo;
                }
                verticesTo.add(fromTo.from - 1);
            }

            int startFromVertex = 0;
            int[] color = new int[n];
            int[] entry = new int[n];
            int[] leave = new int[n];
            LinkedList<Integer> order = new LinkedList<>();

            for (int i = n - 1; i >= 0; i--) {
                if (color[i] == 0) {
                    HashSet<Integer> verticesFrom = list[i];
                    HashSet<Integer> verticesTo = listTo[i];

                    if (verticesFrom == null && verticesTo == null) {
                        color[i] = 2;
                        order.push(i + 1);
                    } else if (verticesTo == null) {
                        dfs(i, list, color, entry, leave, 0, order, writer);
                    }
                }
            }

            for (Integer vertex : order) {
                writer.write(vertex + " ");
            }

            writer.flush();
        }
    }

    private static void dfs(
            int startFromVertex,
            Set<Integer>[] list,
            int[] color,
            int[] entry,
            int[] leave,
            int t,
            LinkedList<Integer> order, BufferedWriter writer) throws IOException {

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
                entry[v] = t++;
                stack.push(v);

//                writer.write(v + 1 + " ");

//              Теперь добавляем в стек все непосещённые соседние вершины, вместо вызова рекурсии.
                Set<Integer> vertices = list[v];
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
                leave[v] = t++;
                order.push(v + 1);
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