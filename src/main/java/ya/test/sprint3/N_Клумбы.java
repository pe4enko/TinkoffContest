package ya.test.sprint3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Вечером ребята решили поиграть в игру «Большое число». Даны числа. Нужно определить, какое самое большое число можно
 * из них составить.
 * <p>
 * Формат ввода В первой строке записано n — количество чисел. Оно не превосходит 100. Во второй строке через пробел
 * записаны n неотрицательных чисел, каждое из которых не превосходит 1000.
 * <p>
 * Формат вывода Нужно вывести самое большое число, которое можно составить из данных чисел.
 */
public class N_Клумбы {

    public static void main(String[] args) throws IOException, RuntimeException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            Interval[] intervals = readIntervalArray(reader, n);

            Arrays.sort(intervals);
            LinkedList<Interval> resultIntervals = new LinkedList<>();

            Integer start = null;
            Integer end = null;

            for (Interval interval : intervals) {
                if (start == null) {
                    start = interval.start;
                    end = interval.end;
                    continue;
                }

//                Подсмотренный вариант
                if (end < interval.start) {
                    resultIntervals.add(new Interval(start, end));
                    start = interval.start;
                    end = interval.end;
                } else if (interval.end > end) {
                    end = interval.end;
                }

//                Мой вариант
//                if (end < interval.start) {
//                    resultIntervals.add(new Interval(start, end));
//                    start = interval.start;
//                }
//                end = Math.max(interval.end, end);
            }

            resultIntervals.add(new Interval(start, end));

            for (Interval resultInterval : resultIntervals) {
                writer.write(resultInterval.start + " " + resultInterval.end + "\n");
            }

            writer.flush();
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static Interval[] readIntervalArray(BufferedReader reader, int n) throws IOException {
        Interval[] intervals = new Interval[n];

        for (int i = 0; i < n; i++) {
            intervals[i] = new Interval(reader.readLine().split(" "));
        }

        return intervals;
    }

    private static class Interval implements Comparable<Interval> {

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public Interval(String[] s) {
            start = Integer.parseInt(s[0]);
            end = Integer.parseInt(s[1]);
        }

        int start;
        int end;

        @Override
        public int compareTo(Interval other) {
            int compareResult = this.start - other.start;
            if (compareResult == 0) {
                return this.end - other.end;
            } else {
                return compareResult;
            }
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

}
