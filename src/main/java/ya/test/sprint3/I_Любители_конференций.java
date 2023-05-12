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
public class I_Любители_конференций {

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
