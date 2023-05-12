package ya.test.sprint3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

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
public class D_Печеньки {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int childrenNumber = readInt(reader);
            int[] factors = readArray(reader);
            int cookieNumber = readInt(reader);
            int[] cookieSizes = readArray(reader);

            Arrays.sort(factors);
            Arrays.sort(cookieSizes);

            int i = 0;
            int j = 0;

            int satisfied = 0;
            while (i < childrenNumber && j < cookieNumber) {
                int factor = factors[i];
                int cookieSize = cookieSizes[j];

                if (factor <= cookieSize) {
                    satisfied++;
                    i++;
                    j++;
                } else {
                    j++;
                }
            }

            writer.write(String.valueOf(satisfied));
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
