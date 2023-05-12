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
 * Рита решила оставить у себя одежду только трёх цветов: розового, жёлтого и малинового. После того как вещи других
 * расцветок были убраны, Рита захотела отсортировать свой новый гардероб по цветам. Сначала должны идти вещи розового
 * цвета, потом —– жёлтого, и в конце —– малинового. Помогите Рите справиться с этой задачей.
 * <p>
 * Примечание: попробуйте решить задачу за один проход по массиву!
 * <p>
 * Формат ввода В первой строке задано количество предметов в гардеробе: n –— оно не превосходит 1000000. Во второй
 * строке даётся массив, в котором указан цвет для каждого предмета. Розовый цвет обозначен 0, жёлтый —– 1, малиновый –—
 * 2.
 * <p>
 * Формат вывода Нужно вывести в строку через пробел цвета предметов в правильном порядке.
 */
public class G_Гардероб {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);

            if (n == 0) {
                return;
            }

            String str = reader.readLine();
            StringTokenizer st = new StringTokenizer(str);

            int center = 0;
            int right = 0;

            while (st.hasMoreTokens()) {
                int elem  = Integer.parseInt(st.nextToken());
                if (elem > 1) {
                    right++;
                } else if (elem < 1) {
                    writer.write(elem + " ");
                } else {
                    center++;
                }
            }

            for (int i = 0; i < center; i++) {
                writer.write("1 ");
            }

            for (int i = 0; i < right; i++) {
                writer.write("2 ");
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
}
