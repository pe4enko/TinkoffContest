package ya.test.sprint7final;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * https://contest.yandex.ru/contest/25597/run-report/69901105/
 * <pre>
 * -- ПРИНЦИП РАБОТЫ --
 * Для поиска итогового значения используется динамическое программирование - когда задача разбивается на более мелкие
 * задачи. Каждая последующая задача зависит от результатов вычисления предыдущей. При этом существуют базовые случай
 * для которых нам известы результаты вычислений, от которых мы и отталкиваемся на последующих шагах работы алгоритма.
 * Для решения задачи необходимо построить такую матрицу dp[sum/2][n] в ячейках которой будет содержаться ответ на вопрос -
 * можно ли собрать сумму i из элементов заданной последовательности от 1 элемента до j.
 * При этом нам точно известно, что собрать сумму 0 можно из любой последовательности элементов включая пустую. Также
 * известно, что если в последовательности нет элементов то из нее невозможно составить сумму отличнуют от 0. Эти два
 * случая являются базовыми от них и будем отталкиваться при построении матрицы.
 * На каждом шаге мы должны вычислить dp[i][j] принимает значение true, если dp[i][j-1] == true (т.е. сумму i можно
 * составить из чисел последовательности до j-1 число), либо если
 * dp[i - значение_текущего_добавляемого_к_возможным_значениям_элементов][j-1] == true (т.е. если сумму отличающуюся
 * от текущей можно составить из элементов без текущего добавляемого то и сумму текущую можно составить прибавив
 * текущий добавляемый элемент)
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Алгоритм корректен по построению.
 * Причина этому следующая: существует некоторое подмножество S, сумма которого равна i для чисел x1, ..., xj
 * тогда и только тогда, когда одно из двух верно:
 * 1) существует подмножество { x1, ..., xj-1 }, дающее сумму i;
 * 2) существует подмножество { x1, ..., xj-1 }, дающее сумму i − xj, поскольку xj + сумма этого подмножества=i.
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Алгоритм работает за O(SUM * n), где SUM - сумма всех элементов последовательности очков заработанных во время
 * турнира, n - кол-во партий.
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * O(SUM)
 * <p>
 * </pre>
 */
public class B_Одинаковые_суммы_1 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = Integer.parseInt(reader.readLine());
            int[] points = readIntArrayWithSize(n, reader);

            int sum = 0;

            for (int i = 0; i < n; i++) {
                sum += points[i];
            }

            if (sum == 0 || sum % 2 != 0) {
                writer.write("False");
                writer.flush();
                return;
            }

            int half = sum / 2;

            boolean[] c0 = new boolean[half + 1];
            boolean[] c1 = new boolean[half + 1];

            c0[0] = true;

            for (int c = 1; c <= n; c++) {
                c1[0] = true;

                for (int r = 1; r <= half; r++) {
                    c1[r] = c0[r];

                    if (r >= points[c - 1]) {
                        c1[r] = c1[r] || c0[r - points[c - 1]];
                    }
                }

                //Это нельзя вынести в метод swap
                boolean[] tmp = c0;
                c0 = c1;
                c1 = tmp;
            }

            if (c0[half]) {
                writer.write("True");
            } else {
                writer.write("False");
            }
            writer.flush();
        }
    }

    private static int[] readIntArrayWithSize(int size, BufferedReader reader) throws IOException {

        final int[] result = new int[size];
        int i = 0;

        StringTokenizer st = new StringTokenizer(reader.readLine());
        while (st.hasMoreTokens()) {
            result[i] = Integer.parseInt(st.nextToken());
            i++;
        }

        return result;
    }
}
