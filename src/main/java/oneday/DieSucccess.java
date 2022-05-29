package oneday;

import java.util.Scanner;

/**
 * <a href="https://www.thecodingshala.com/2021/05/maximum-population-year-leetcode-solution.html">...</a>
 */
public class DieSucccess {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int[][] logs = new int[length][];

        for (int i = 0; i < length; i++) {
            logs[i] = new int[]{scanner.nextInt(), scanner.nextInt()};
        }

        System.out.println(findMaximum(logs));
    }

    public static int findMaximum(int[][] logs) {
        int[] arr = new int[101];

        for (int[] log : logs) {
            int start = log[0] - 1980;
            int end = log[1] - 1980;
            arr[start]++;
            arr[end]--;
        }

        int res = 0;
        int max = arr[0];
        for (int i = 1; i < 101; i++) {
            arr[i] += arr[i - 1];
            if (arr[i] > max) {
                max = arr[i];
                res = i;
            }
        }
        return res + 1980;
    }
}