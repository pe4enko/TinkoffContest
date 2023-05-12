package ya.test.sprint3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Вася решил накопить денег на два одинаковых велосипеда — себе и сестре. У Васи есть копилка, в которую каждый день он
 * может добавлять деньги (если, конечно, у него есть такая финансовая возможность). В процессе накопления Вася не
 * вынимает деньги из копилки.
 * <p>
 * У вас есть информация о росте Васиных накоплений — сколько у Васи в копилке было денег в каждый из дней.
 * <p>
 * Ваша задача — по заданной стоимости велосипеда определить
 * <p>
 * первый день, в которой Вася смог бы купить один велосипед, и первый день, в который Вася смог бы купить два
 * велосипеда. Подсказка: решение должно работать за O(log n).
 * <p>
 * Формат ввода В первой строке дано число дней n, по которым велись наблюдения за Васиными накоплениями. 1 ≤ n ≤ 106.
 * <p>
 * В следующей строке записаны n целых неотрицательных чисел. Числа идут в порядке неубывания. Каждое из чисел не
 * превосходит 106.
 * <p>
 * В третьей строке записано целое положительное число s — стоимость велосипеда. Это число не превосходит 106.
 * <p>
 * Формат вывода Нужно вывести два числа — номера дней по условию задачи.
 * <p>
 * Если необходимой суммы в копилке не нашлось, нужно вернуть -1 вместо номера дня.
 * <p>
 * Пример 1 Ввод 6 1 2 4 4 6 8 3
 * <p>
 * Вывод 3 5
 * <p>
 * Пример 2 Ввод 6 1 2 4 4 4 4 3
 * <p>
 * Вывод 3 -1
 * <p>
 * Пример 3 Ввод 6 1 2 4 4 4 4 10
 * <p>
 * Вывод -1 -1
 */
public class L_Два_велосипеда {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            readInt(reader);

            int[] cashArray = readIntArray(reader);
//            int[] cashArray = readArray(reader);

            int price = readInt(reader);

            writer.write(findDay(price, cashArray) + " " + findDay(price * 2, cashArray));
            writer.flush();
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static int[] readIntArray(BufferedReader reader) throws IOException {
/*
        LinkedList<Integer> tmp = new LinkedList<>();

        StringTokenizer st = new StringTokenizer(reader.readLine());
        while (st.hasMoreTokens()) {
            int i = Integer.parseInt(st.nextToken());
            tmp.add(i);
        }

        final int[] result = new int[tmp.size()];
        int i = 0;

        for (Integer integer : tmp) {
            result[i] = (integer == null ? 0 : integer);
            i++;
        }
        return result;
*/

        String str = reader.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int memSize = 0;

        while (st.hasMoreTokens()) {
            st.nextToken();
            memSize++;
        }

        st = new StringTokenizer(str);
        final int[] result = new int[memSize];

        int i=0;
        while (st.hasMoreTokens()) {
            result[i] = Integer.parseInt(st.nextToken());
            i++;
        }

        return result;
    }

    private static int findDay(int needCash, int[] cash) {
        int foundDay = -2;

        int left = 0;
        int right = cash.length - 1;

        do {
            int rightMinusLeft = right - left;
            int mid = left + (rightMinusLeft) / 2;

            if (cash[mid] >= needCash) {
                foundDay = mid;
                right = mid;
            } else {
                left = mid + 1;
            }

            if (rightMinusLeft <= 1) {
                return foundDay + 1;
            }

        } while (true);
    }
}
