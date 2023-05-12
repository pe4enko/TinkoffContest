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
 * Вам дан ориентированный граф. Известно, что все его вершины достижимы из вершины s=1. Найдите время входа и выхода
 * при обходе в глубину, производя первый запуск из вершины s. Считайте, что время входа в стартовую вершину равно 0.
 * Соседей каждой вершины обходите в порядке увеличения номеров.
 * <p>
 * Формат ввода В первой строке дано число вершин n (1 ≤ n ≤ 2⋅ 105) и рёбер (0 ≤ m ≤ 2 ⋅ 105). В каждой из следующих m
 * строк записаны рёбра графа в виде пар (from, to), 1 ≤ from ≤ n — начало ребра, 1 ≤ to ≤ n — его конец. Гарантируется,
 * что в графе нет петель и кратных рёбер.
 * <p>
 * Формат вывода Выведите n строк, в каждой из которых записана пара чисел tini, touti — время входа и выхода для
 * вершины i.
 */
public class H_Время_выходить {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            FromTo crutch = readFromToPair(reader);
            int n = crutch.from;
            int m = crutch.to;

            @SuppressWarnings("unchecked") TreeSet<Integer>[] list = new TreeSet[n];

            for (int i = 0; i < m; i++) {
                FromTo fromTo = readFromToPair(reader);
                TreeSet<Integer> vertices = list[fromTo.from - 1];
                if (vertices == null) {
                    vertices = new TreeSet<>(Comparator.reverseOrder());
                    list[fromTo.from - 1] = vertices;
                }
                vertices.add(fromTo.to - 1);

//                TreeSet<Integer> verticesTo = list[fromTo.to - 1];
//                if (verticesTo == null) {
//                    verticesTo = new TreeSet<>(Comparator.reverseOrder());
//                    list[fromTo.to - 1] = verticesTo;
//                }
//                verticesTo.add(fromTo.from - 1);
            }

            int startFromVertex = 0;
            int[] color = new int[n];
            int[] entry = new int[n];
            int[] leave = new int[n];

            dfs(startFromVertex, list, color, entry, leave, 0, writer);

            for (int i = 0; i < entry.length; i++) {
                int e = entry[i];
                int l = leave[i];

                writer.write(e + " " + l + "\n");
            }

            writer.flush();
        }
    }

    private static void dfs(
            int startFromVertex,
            TreeSet<Integer>[] list,
            int[] color,
            int[] entry,
            int[] leave,
            int t,
            BufferedWriter writer) throws IOException {

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
                leave[v] = t++;
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