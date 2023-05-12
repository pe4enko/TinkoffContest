package ya.test.sprint8final;

import static java.lang.System.in;
import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://contest.yandex.ru/contest/26133/run-report/70455750/
 * <pre>
 * -- ПРИНЦИП РАБОТЫ --
 * 1. Составим префиксное дерево из слов из которых может состоять текст.
 * 2. Для поиска решения будем использовать динамическое программирование - т.е. метод решения задачи путем разбиения
 * ее на более мелкие подзадачи.
 * Отвечаем на 5 вопросов:
 * 1) Что будет храниться в массиве dp?
 *    В массиве dp будет храниться признак возможно ли строку до i символа построить используя строки из набора
 * возможных к использованию.
 * 2) Каким будет базовый случай?
 *    Базовый случай будет отвечать на вопрос можно ли построить строку нулевой длины из данных слов? Отвечая на него
 * можно сказать что да, т.е. dp[0] = true
 * 3) Каким будет переход динамики?
 *    Если мы можем построить строку до i символа, о чем говорит значение true в dp, тогда в таком случае проверяем
 * дальше, можем ли мы отталкиваясь начиная от символа i построить еще часть строки. Для этого на каждом шаге добавляем
 * по одному символу из исходного текста и проверяем есть ли среди возможных строк такие которые равны полученной
 * подстроке от i до i + текущиц шаг. Если в итоге нашли такую строку то значит записываем в dp[i + текущиц шаг]
 * значение true. Если не нашли то продолжаем двигаться по исходной строке и добавлять символы в подстроку до тех пор
 * пока не дойдем до последнего сивола.
 * Для того чтобы сократить время поиска мы будет использовать префиксное дерево. Таким образом шаг с поиском строки
 * соответствующей выделенной подстроке будет заменен на последовательный перебор префиксного дерева.
 * 4) Каким будет порядок вычисления данных в массиве dp?
 * Будем заполнять последовательно начиная с 1 элемента. При этом возможно что на следующих щагах массив dp будет
 * уточняться.
 * 5) Где будет ответ на искомый вопрос? Он будет в последней ячейке dp.
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Алгоритм корректен по построению.
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * 1. Составление префиксного дерева зависит от кол-ва слов в справочнике и от их длины.
 * O(L) где L сумма всех символов строк справочника.
 * O(n * |s|), где n - кол-во строк в справочнике, |s| - длина максимамльной строки
 * 2. Для каждого символа текста мы перебираем в худшем случае все узлы длинной до длины текста.
 * O(|T| * |T|), где |T| - длина текста.
 * Итого: O(L + |T| * |T|)
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * 1. Хранение текста O(|T|), где |T| - длина текста.
 * 2. Хранение префиксного дерева, в каждом узле хранится массив длинной 26 элементов.
 * Всего узлов L сумма всех символов строк справочника. O(L).
 * 3. Массив динамического программирования dp O(|T|)
 * Итого: O(|T| + L)
 * </pre>
 */
public class B_Шпаргалка {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))) {

            String str = reader.readLine();
            int n = Integer.parseInt(reader.readLine());

            Node trie = fillTrie(reader, n);

            if (canSplit(str, trie)) {
                writer.write("YES");
            } else {
                writer.write("NO");
            }

            writer.flush();
        }
    }

    private static boolean canSplit(String str, Node trie) {
        //можно ли составить строку по i символ из элементов префиксного дерева.
        boolean[] dp = new boolean[str.length() + 1];
        //считаем, что пустую строку мы можем составить из любых последовательностей строк, точнее нам совсем
        //не нужны никакие строки.
        dp[0] = true;

        for (int i = 0; i < str.length(); i++) {
            Node currentNode = trie;

            if (!dp[i]) {
                //Если мы не смогли составить строку до i символа, то и дальше отталкиваться от i символа мы не
                //можем. Т.е. не можем строить строку после i символа, если до i символа мы не смогли построить.
                //С другой стороны мы могли уже построить строку дальше i символа, т.е. мы можем продолжить
                //строить строку отталкиваясь от какого то симваола с индексом больше i.
                //Поэтому продолжим поиск такого i символа от которого можно продолжить построение строки из
                //элементов префиксного дерева.
                continue;
            }

            for (int j = i + 1; j <= str.length(); j++) {
                char symbol = str.charAt(j - 1);

                currentNode = currentNode.trie[Node.getIndex(symbol)];

                if (currentNode == null) {
                    break;
                }

                if (currentNode.isTerminal) {
                    dp[j] = true;
                }
            }
        }

        return dp[str.length()];
    }

    private static Node fillTrie(BufferedReader reader, int n) throws IOException {
        Node trie = new Node();

        for (int i = 0; i < n; i++) {
            addToTrie(reader.readLine(), trie);
        }
        return trie;
    }

    private static void addToTrie(String text, Node trie) {
        Node currentNode = trie;

        for (int j = 0; j < text.length(); j++) {
            char c = text.charAt(j);

            boolean terminal = (j == (text.length() - 1));

            Node findNode = currentNode.trie[Node.getIndex(c)];
            if (findNode == null) {
                findNode = new Node();
                currentNode.trie[Node.getIndex(c)] = findNode;
            }

            if (terminal) {
                findNode.isTerminal = true;
            }

            currentNode = findNode;
        }
    }

    public static class Node {

        Node[] trie = new Node[26];

        boolean isTerminal;

        public static int getIndex(char symbol) {
            return symbol - 'a';
        }
    }
}
