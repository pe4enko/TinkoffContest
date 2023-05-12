package ya.test.sprint6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * Алла пошла на стажировку в студию графического дизайна, где ей дали такое задание: для очень большого числа
 * ориентированных графов преобразовать их список рёбер в список смежности. Чтобы побыстрее решить эту задачу, она
 * решила автоматизировать процесс.
 * <p>
 * Помогите Алле написать программу, которая по списку рёбер графа будет строить его список смежности.
 * <p>
 * Формат ввода В первой строке дано число вершин n (1 ≤ n ≤ 100) и число ребер m (1 ≤ m ≤ n(n-1)). В следующих m
 * строках заданы ребра в виде пар вершин (u,v), если ребро ведет от u к v.
 * <p>
 * Формат вывода Выведите информацию о рёбрах, исходящих из каждой вершины.
 * <p>
 * В строке i надо написать число рёбер, исходящих из вершины i, а затем перечислить вершины, в которые ведут эти рёбра
 * –— в порядке возрастания их номеров.
 */
public class A_Построить_список_смежности {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            FromTo crutch = readFromToPair(reader);
            int n = crutch.from;
            int m = crutch.to;

            List[] adjacents = new LinkedList[n];

            for (int i = 0; i < m; i++) {
                FromTo fromTo = readFromToPair(reader);
                List<Integer> list = adjacents[fromTo.from - 1];
                if (list == null) {
                    list = new LinkedList<>();

                    adjacents[fromTo.from - 1] = list;
                }
                list.add(fromTo.to);
            }

            for (List adjacent : adjacents) {
                if (adjacent == null) {
                    writer.write("0\n");
                } else {
                    writer.write(adjacent.size() + " " + adjacent.stream().sorted().map(Object::toString)
                            .collect(Collectors.joining(" ")) + "\n");

                }

            }

            writer.flush();
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
