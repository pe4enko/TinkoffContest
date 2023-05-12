package ya.test.sprint7final;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * https://contest.yandex.ru/contest/25597/run-report/69885208/
 * <pre>
 * -- ПРИНЦИП РАБОТЫ --
 * https://www.geeksforgeeks.org/partition-problem-dp-18/
 * https://ru.wikipedia.org/wiki/%D0%97%D0%B0%D0%B4%D0%B0%D1%87%D0%B0_%D1%80%D0%B0%D0%B7%D0%B1%D0%B8%D0%B5%D0%BD%D0%B8%D1%8F_%D0%BC%D0%BD%D0%BE%D0%B6%D0%B5%D1%81%D1%82%D0%B2%D0%B0_%D1%87%D0%B8%D1%81%D0%B5%D0%BB
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
 * O(SUM * n)
 * <p>
 * </pre>
 */
public class B_Одинаковые_суммы {

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

            boolean[][] dp = new boolean[half + 1][n + 1];

            //Сумму 0 мы можем составить из любомого набора чисел, в том числе если набор пустой.
            for (int i = 0; i <= n; i++) {
                dp[0][i] = true;
            }

            //Если в наборе нет чисел, то из этого набора можно составить только сумму 0
            // Действие не обязательное, т.к. по умолчанию массив имеет все значения равными false
//            for (int i = 1; i <= half; i++) {
//                dp[i][0] = false;
//            }

            //Тут i - сумма, которую необходимо составить
            // j - из каких элементов массива points (с 0 до j-1 элементов) эту сумму мы можем составлять
            for (int i = 1; i <= half; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dp[i][j - 1]) {
                        //Если из предыдущих j-1 элементов мы смогли составить сумму i, то и из j кол-ва элементов
                        //мы так же сможем составить эту сумму
                        dp[i][j] = dp[i][j - 1];
                    } else if (i >= points[j - 1]) {
                        //Если сумма i больше либо равна элементу j
                        // (на который мы расширяем изучаемую последовательность)
                        dp[i][j] = dp[i - points[j - 1]][j - 1];
                    } /*else {
                        //Так как по умолчанию все элементы равны false то этот шаг делать необязательно.
                        //Оставлено для того чтобы было явно видно, что данный случай учтен.
                        dp[i][j] = false;
                    }*/
                }
            }

/*
            // Раскоментировать на случай необходимости вывод таблицы с промежуточными значениями
            for (int i = 0; i <= half; i++) {
                for (int j = 0; j <= n; j++) {
                    System.out.printf("%6b", dp[i][j]);
                }

                System.out.printf("\n");
            }
*/

            if (dp[half][n]) {
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
