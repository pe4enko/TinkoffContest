package ya.test.sprint3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Задача повышенной сложности
 * <p>
 * На каждом острове в архипелаге Алгосы живёт какое-то количество людей или же остров необитаем (тогда на острове живёт
 * 0 людей). Пусть на i-м острове численность населения составляет ai. Тимофей захотел найти медиану среди всех значений
 * численности населения.
 * <p>
 * Определение: Медиана (https://ru.wikipedia.org/wiki/Медиана_(статистика)) массива чисел a_i —– это такое число, что
 * половина чисел из массива не больше него, а другая половина не меньше. В общем случае медиану массива можно найти,
 * отсортировав числа и взяв среднее из них. Если количество чисел чётно, то возьмём в качестве медианы полусумму
 * соседних средних чисел, (a[n/2] + a[n/2 + 1])/2.
 * <p>
 * У Тимофея уже есть отдельно данные по северной части архипелага и по южной, причём значения численности населения в
 * каждой группе отсортированы по неубыванию.
 * <p>
 * Определите медианную численность населения по всем островам Алгосов.
 * <p>
 * Подсказка: Если n –— число островов в северной части архипелага, а m –— в южной, то ваше решение должно работать за
 * .
 * <p>
 * Формат ввода В первой строке записано натуральное число n, во второй —– натуральное число m. Они не превосходят 10
 * 000.
 * <p>
 * Далее в строку через пробел записаны n целых неотрицательных чисел, каждое из которых не превосходит 10 000, –—
 * значения численности населения в северной части Алгосов.
 * <p>
 * В последней строке через пробел записаны m целых неотрицательных чисел, каждое из которых не превосходит 10 000 –—
 * значения численности населения в южной части Алгосов.
 * <p>
 * Значения в третьей и четвёртой строках упорядочены по неубыванию.
 * <p>
 * Формат вывода Нужно вывести одной число — найденную медиану.
 */
public class M_Золотая_середина {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            int[] vuzIds = readIntArray(reader);
            int printFirst = readInt(reader);

            ArrayList<Vuz> vuzes = new ArrayList<>(10000);
            for (int i = 0; i < 10000; i++) {
                vuzes.add(new Vuz(i));
            }

            for (int vuzId : vuzIds) {
                vuzes.get(vuzId).studentsCount++;
            }

            LinkedList<Vuz> v = new LinkedList<>();
            for (Vuz vuze : vuzes) {
                if (vuze.studentsCount > 0) {
                    v.add(vuze);
                }
            }

            v.sort(
                    Comparator.comparingInt(Vuz::getStudentsCount)
                            .reversed()
                            .thenComparingInt(Vuz::getId));

            for (int i = 0; i < printFirst; i++) {
                writer.write(v.get(i).id + " ");
            }

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
    }

    private static int[] readIntArrayMemOptimized(BufferedReader reader) throws IOException {

        String str = reader.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int memSize = 0;

        while (st.hasMoreTokens()) {
            st.nextToken();
            memSize++;
        }

        st = new StringTokenizer(str);
        final int[] result = new int[memSize];

        int i = 0;
        while (st.hasMoreTokens()) {
            result[i] = Integer.parseInt(st.nextToken());
            i++;
        }

        return result;
    }

    private static class Vuz {

        int id;
        int studentsCount;

        public Vuz(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public int getStudentsCount() {
            return studentsCount;
        }
    }
}
