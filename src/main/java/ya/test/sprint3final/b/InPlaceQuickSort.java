package ya.test.sprint3final.b;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * todo:
 */
public class InPlaceQuickSort {

    public static void main(String[] args) throws IOException {
        {
            int[] arr = {3, 2, 1};
            sort(arr);

            int[] expected = {1, 2, 3};
            assert Arrays.equals(arr, expected);
        }

        {
            int[] arr = {19, 21, 100, 101, 1, 4, 5, 7, 12};
            sort(arr);

            int[] expected = {1, 4, 5, 7, 12, 19, 21, 100, 101};
            assert Arrays.equals(arr, expected);
        }

        /*
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
*/
    }

    public static void sort(int[] arr) {
        sortRecursive(arr, 0, arr.length - 1);
    }

    private static void sortRecursive(int[] arr, int from, int to) {
        if (from >= to) {
            return;
        }


        //pivotPosition - уже на свое месте по этому ее сортировать не нужно и индекс pivotPosition - 1
        int pivotPosition = partition(arr, from, to);

        sortRecursive(arr, from, pivotPosition - 1);
        sortRecursive(arr, pivotPosition + 1, to);
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];

        //указывает на крайний правый элемент из отрезка со значениями <= pivot
        int i = left - 1;

        //j всегда указывает на самый левый элемент из отрезка со значениями > pivot
        for (int j = left; j <= right - 1; j++) {
            if (arr[j] <= pivot) {
                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[right];
        arr[right] = temp;

        return i + 1;
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
