package ya.test.sprint1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * Legend
 * Алла придумала такую онлайн-игру: игрок нажимает на кнопку, и на экране появляются три случайных числа.
 * Если все три числа оказываются одной чётности, игрок выигрывает.
 *
 * Напишите программу, которая по трём числам определяет, выиграл игрок или нет.
 *
 * Input format
 * В первой строке записаны три случайных целых числа a, b и c. Числа не превосходят 109 по модулю.
 *
 * Output format
 * Выведите «WIN», если игрок выиграл, и «FAIL» в противном случае.
 * </pre>
 */
public class B_Event_Odd_Numbers {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            List<Long> numbers = readList(reader);
            long a = numbers.get(0) % 2;
            long b = numbers.get(1) % 2;
            long c = numbers.get(2) % 2;

            if (((a == 1 || a == -1) && (b == 1 || b == -1) && (c == 1 || c == -1)) || (a == 0 && b == 0 && c == 0)) {
                writer.write("WIN");
            } else {
                writer.write("FAIL");
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Long> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }
}
