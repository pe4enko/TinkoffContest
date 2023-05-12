package ya.test.sprint8;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * На IT-конференции присутствовали студенты из разных вузов со всей страны. Для каждого студента известен ID
 * университета, в котором он учится.
 * <p>
 * Тимофей предложил Рите выяснить, из каких k вузов на конференцию пришло больше всего учащихся.
 * <p>
 * Формат ввода В первой строке дано количество студентов в списке —– n (1 ≤ n ≤ 15 000).
 * <p>
 * Во второй строке через пробел записаны n целых чисел —– ID вуза каждого студента. Каждое из чисел находится в
 * диапазоне от 0 до 10 000.
 * <p>
 * В третьей строке записано одно число k.
 * <p>
 * Формат вывода Выведите через пробел k ID вузов с максимальным числом участников. Они должны быть отсортированы по
 * убыванию популярности (по количеству гостей от конкретного вуза). Если более одного вуза имеет одно и то же
 * количество учащихся, то выводить их ID нужно в порядке возрастания.
 */
public class J_Случай_верблюда {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))) {

            Node trie = new Node();

            int n = readInt(reader);
            for (int i = 0; i < n; i++) {
                String text = reader.readLine();
                trie.addText(text);
                fillTrie(text, trie);
            }

            int m = readInt(reader);
            for (int i = 0; i < m; i++) {
                String pattern = reader.readLine();

                List<String> strings = findAny(trie, pattern);
                strings.sort(Comparator.naturalOrder());

                if (strings.isEmpty()) {
                    out.println();
                } else {
                    strings.forEach(out::println);
                }
            }

            writer.flush();
        }
    }

    private static List<String> findAny(Node trie, String pattern) {
        Node currentNode = trie;

        if (pattern.isEmpty()) {
            return trie.texts;
        }

        for (int pos = 0; pos < pattern.length(); pos++) {
            char symbol = pattern.charAt(pos);
            boolean isLastSymbol = (pos == (pattern.length() - 1));

            Node findNode = currentNode.trie[symbol - 'A'];
            if (findNode == null) {
                return Collections.emptyList();
            } else {
                if (isLastSymbol) {
                    return findNode.texts;
                }

                currentNode = findNode;
            }
        }

        return Collections.emptyList();
    }

    private static void fillTrie(String text, Node trie) {
        Node currentNode = trie;

        for (int j = 0; j < text.length(); j++) {
            char c = text.charAt(j);

            if (c > 'Z') {
                continue;
            }

            boolean terminal = (j == (text.length() - 1));

            Node findNode = currentNode.trie[c - 'A'];
            if (findNode == null) {
                findNode = new Node();
                currentNode.trie[c - 'A'] = findNode;
            }

            if (terminal) {
                findNode.isTerminal = true;
            }

            findNode.addText(text);

            currentNode = findNode;
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    public static class Node {

        Node[] trie = new Node[26];
        boolean isTerminal;

        ArrayList<String> texts;

        public void addText(String text) {
            if (texts == null) {
                texts = new ArrayList<>();
            }
            texts.add(text);
        }
    }
}
