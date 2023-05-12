package ya.test.sprint3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
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
public class I_Любители_hash {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            ArrayList<Integer> vuzIds = readArrayList(reader);
            int printFirst = readInt(reader);

            HashMap<Integer, Integer> map = new HashMap<>();

            for (int vuzId : vuzIds) {
                //compute медленный
//                map.compute(vuzId, (k, v) -> (v == null) ? 1 : ++v);
                Integer integer = map.get(vuzId);
                if (integer == null) {
                    map.put(vuzId, 0);
                } else {
                    map.put(vuzId, ++integer);
                }
            }

            ArrayList<Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());

            Comparator<Entry<Integer, Integer>> valueComparator = Entry.comparingByValue(Comparator.reverseOrder());
            Comparator<Entry<Integer, Integer>> keyComparator = Entry.comparingByKey();

            list.sort(
                    valueComparator.thenComparing(keyComparator)
            );

            for (int i = 0; i < printFirst; i++) {
                writer.write(list.get(i).getKey() + " ");
            }

            writer.flush();
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
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

    private static ArrayList<Integer> readArrayList(BufferedReader reader) throws IOException {

        ArrayList<Integer> tmp = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(reader.readLine());
        while (st.hasMoreTokens()) {
            int i = Integer.parseInt(st.nextToken());
            tmp.add(i);
        }

        return tmp;
    }
}
