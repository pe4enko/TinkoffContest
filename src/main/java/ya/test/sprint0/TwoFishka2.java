package ya.test.sprint0;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * https://contest.yandex.ru/contest/26365/problems/E/
 */
public class TwoFishka2 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in)); BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(System.out))) {
            int n = readInt(reader);
            List<Integer> arr = readList(reader);
            Integer sum = readInt(reader);

            Set<Integer> previous = new HashSet<>();

            for (Integer a : arr) {
                int y = sum - a;

                if (previous.contains(y)) {
                    writer.write(a + " " + y);
                    writer.flush();
                    System.exit(0);
                } else {
                    previous.add(a);
                }
            }
            writer.write("None");
        }
    }


    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().split(" ")).stream().map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }
}