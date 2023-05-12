package ya.test.sprint6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * Вам дан неориентированный граф. Найдите его компоненты связности.
 * <p>
 * Формат ввода В первой строке дано количество вершин n (1≤ n ≤ 105) и рёбер m (0 ≤ m ≤ 2 ⋅ 105). В каждой из следующих
 * m строк записано по ребру в виде пары вершин 1 ≤ u, v ≤ n.
 * <p>
 * Гарантируется, что в графе нет петель и кратных рёбер.
 * <p>
 * Формат вывода Выведите все компоненты связности в следующем формате: в первой строке выведите общее количество
 * компонент.
 * <p>
 * Затем на отдельных строках выведите вершины каждой компоненты, отсортированные по возрастанию номеров. Компоненты
 * между собой упорядочивайте по номеру первой вершины.
 */
public class E_Компоненты_связности {

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
                    verticesFrom = new TreeSet<>();
                    list[fromTo.from - 1] = verticesFrom;
                }
                verticesFrom.add(fromTo.to - 1);

                TreeSet<Integer> verticesTo = list[fromTo.to - 1];
                if (verticesTo == null) {
                    verticesTo = new TreeSet<>();
                    list[fromTo.to - 1] = verticesTo;
                }
                verticesTo.add(fromTo.from - 1);
            }

            int startFromVertex = 0;
            int[] color = new int[n];
            int[] components = new int[n];
            int[] entry = new int[n];
            int[] leave = new int[n];
            LinkedList<Integer> order = new LinkedList<>();

            int paintColor = 0;
            for (int i = 0; i < n; i++) {

                if (color[i] == 0) {
                        dfs(i, list, color, entry, leave, 0, order, writer, components, paintColor);
                        paintColor++;
                }
            }

            writer.write(paintColor + "\n");

            for (int i = 0; i < paintColor; i++) {
                for (int j = 0; j < components.length; j++) {
                    int component = components[j];
                    if (component == i) {
                        writer.write(j + 1 + " ");
                    }
                }
                writer.write("\n");
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
            LinkedList<Integer> order, BufferedWriter writer, int[] components, int paintColor) throws IOException {

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
                components[v] = paintColor;
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