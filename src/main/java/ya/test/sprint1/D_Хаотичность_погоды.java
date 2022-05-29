package ya.test.sprint1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import org.w3c.dom.ls.LSOutput;

/**
 * Метеорологическая служба вашего города решила исследовать погоду новым способом. Под температурой воздуха в
 * конкретный день будем понимать максимальную температуру в этот день. Назовём хаотичностью погоды за n дней количество
 * дней, в которые температура строго больше, чем в день до (если такой существует) и в день после текущего (если такой
 * существует). Например, если за 5 дней максимальная температура воздуха составляла [1, 2, 5, 4, 8] градусов, то
 * хаотичность за этот период равна 2: в 3-й и 5-й дни выполнялись описанные условия. Определите по ежедневным
 * показаниям температуры хаотичность погоды за этот период.
 * <p>
 * Заметим, что если число показаний n=1, то единственный день будет хаотичным.
 */
public class D_Хаотичность_погоды {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);

            if (n == 1) {
                writer.write(String.valueOf(1));
                writer.flush();
                return;
            }

            int[] arr = readList(reader);

            int randomnessDays = 0;

            for (int i = 0; i < n; i++) {
                Integer left = i-1 >= 0 ? arr[i - 1] : null;
                int mid = arr[i];
                Integer right = (i + 1 > n - 1) ? null : arr[i + 1];

                if ((left == null || left < mid) && (right == null || mid > right)) {
//                    System.out.printf("Left: %d, Mid: %d, Right: %d\n", left, mid, right);
                    randomnessDays++;
                }
            }

            writer.write(String.valueOf(randomnessDays));
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
