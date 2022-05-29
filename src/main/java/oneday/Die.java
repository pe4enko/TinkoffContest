package oneday;

import java.util.Scanner;

/**
 * Не правильная реализация, не учитывает промежуточные значения. Правильная описана в статье.
 * <a href="https://www.thecodingshala.com/2021/05/maximum-population-year-leetcode-solution.html">...</a>
 */
public class Die {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int[][] logs = new int[length][];

        for (int i = 0; i < length; i++) {
            logs[i] = new int[] { scanner.nextInt(), scanner.nextInt() };
        }

        System.out.println(findMaximum(logs));
    }

    private static int findMaximum(int[][] logs) {
        if (logs.length == 0) {
            return 0;
        }

        int resultYear = logs[0][0];
        int resultIntersections = 0;

        for (int[] log1 : logs) {
            int from1 = log1[0];
            int to1 = log1[1];

            int intersections = 0;

            for (int[] log2 : logs) {
                int from2 = log2[0];
                int to2 = log2[1];

                if (from1 >= from2 && from1 < to2) {
                    intersections++;
                }
            }

            if (intersections > resultIntersections) {
                resultYear = from1;
                resultIntersections = intersections;
            }
        }

        return resultYear;
    }
}