package ya.test.sprint7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * В мире последовательностей нет гороскопов. Поэтому когда две последовательности хотят понять, могут ли они счастливо
 * жить вместе, они оценивают свою совместимость как длину их наибольшей общей подпоследовательности.
 * <p>
 * Подпоследовательность получается из последовательности удалением некоторого (возможно, нулевого) числа элементов. То
 * есть элементы сохраняют свой относительный порядок, но не обязаны изначально идти подряд.
 * <p>
 * Найдите наибольшую общую подпоследовательность двух одиноких последовательностей и выведите её!
 * <p>
 * Формат ввода В первой строке дано число n — количество элементов в первой последовательности (1 ≤ n ≤ 1000). Во
 * второй строке даны n чисел ai (0 ≤ |ai| ≤ 109) — элементы первой последовательности. Аналогично в третьей строке дано
 * m (1 ≤ m ≤ 1000) — число элементов второй последовательности. В четвертой строке даны элементы второй
 * последовательности через пробел bi (0 ≤ |bi| ≤ 109).
 * <p>
 * Формат вывода Сначала выведите длину найденной наибольшей общей подпоследовательности, во второй строке выведите
 * индексы элементов первой последовательности, которые в ней участвуют, в третьей строке — индексы элементов второй
 * последовательности. Нумерация индексов с единицы, индексы должны идти в корректном порядке.
 * <p>
 * Если возможных НОП несколько, то выведите любую.
 */
public class K_Гороскопы {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            int[] a = readIntArrayWithSize(n, reader);

            int m = readInt(reader);
            int[] b = readIntArrayWithSize(m, reader);

            int[][] dp = new int[n + 1][m + 1];

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (a[i - 1] == b[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }

            LinkedList<Integer> answer = new LinkedList<>();
            LinkedList<Integer> answerA = new LinkedList<>();
            LinkedList<Integer> answerB = new LinkedList<>();

            int i = n;
            int j = m;

            while (dp[i][j] != 0) {
                if (a[i - 1] == b[j - 1]) {
                    answer.push(a[i - 1]);
                    answerA.push(i);
                    answerB.push(j);
                    //переместиться в клетку dp[i-1][j-1]
                    i--;
                    j--;
                } else {
                    //Если A[i] ≠ B[j], то один из символов в строках точно не входит в НОП.
                    if (dp[i][j] == dp[i - 1][j]) {
                        //Если dp[i][j] = dp[i-1][j], значит, существует НОП, в которую A[i] точно не входит.
                        // Перемещаемся вверх — в соседнюю ячейку.
                        i--;
                    } else {
                        //Иначе — если dp[i][j] = dp[i][j-1], значит, существует НОП, в которую B[j] не входит.
                        // Перемещаемся в ячейку, расположенную левее текущей.
                        j--;
                    }
                }
            }

            writer.write(dp[n][m] + "\n");
            writer.write(answerA.stream().map(Objects::toString).collect(Collectors.joining(" ")) + "\n");
            writer.write(answerB.stream().map(Objects::toString).collect(Collectors.joining(" ")));
            writer.flush();
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
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
