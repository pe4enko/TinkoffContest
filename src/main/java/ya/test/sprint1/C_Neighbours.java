package ya.test.sprint1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 */
public class C_Neighbours {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            //Кол-во строк
            int n = readInt(reader);
            //Кол-во столбцов
            int m = readInt(reader);

            int[][] arr = new int[n][m];

            for (int i = 0; i < n; i++) {
                arr[i] = readList(reader);
            }

            int n1 = readInt(reader);
            int m1 = readInt(reader);

            ArrayList<Integer> resultList = new ArrayList<>();

            if (n1 - 1 >= 0) {
                resultList.add(arr[n1 - 1][m1]);
            }

            if (n1 + 1 <= n - 1) {
                resultList.add(arr[n1 + 1][m1]);
            }

            if (m1 - 1 >= 0) {
                resultList.add(arr[n1][m1 - 1]);
            }

            if (m1 + 1 <= m - 1) {
                resultList.add(arr[n1][m1 + 1]);
            }

            String result = resultList.stream()
                    .sorted()
                    .map(Objects::toString)
                    .collect(Collectors.joining(" "));

            writer.write(result);
            writer.flush();
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
