package ya.test.sprint3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * На клавиатуре старых мобильных телефонов каждой цифре соответствовало несколько букв. Примерно так:
 * <p>
 * 2:'abc', 3:'def', 4:'ghi', 5:'jkl', 6:'mno', 7:'pqrs', 8:'tuv', 9:'wxyz'
 * <p>
 * Вам известно в каком порядке были нажаты кнопки телефона, без учета повторов. Напечатайте все комбинации букв,
 * которые можно набрать такой последовательностью нажатий.
 * <p>
 * Формат ввода На вход подается строка, состоящая из цифр 2-9 включительно. Длина строки не превосходит 10 символов.
 * <p>
 * Формат вывода Выведите все возможные комбинации букв через пробел.
 */
public class B_Комбинации {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            Map<Integer, List<Character>> buttons = Map.of(
                    2, List.of('a', 'b', 'c'),
                    3, List.of('d', 'e', 'f'),
                    4, List.of('g', 'h', 'i'),
                    5, List.of('j', 'k', 'l'),
                    6, List.of('m', 'n', 'o'),
                    7, List.of('p', 'q', 'r', 's'),
                    8, List.of('t', 'u', 'v'),
                    9, List.of('w', 'x', 'y', 'z')
            );

            int[] numbers = Arrays.stream(reader.readLine().split("")).mapToInt(Integer::parseInt).toArray();

            f(numbers, writer, buttons, "", 0);

            writer.flush();
        }
    }

    private static void f(int[] numbers, BufferedWriter writer, Map<Integer, List<Character>> buttons, String prefix,
            int deep)
            throws IOException {

        List<Character> symbols = buttons.get(numbers[deep]);
        if (deep == numbers.length - 1) {
            for (Character symbol : symbols) {
                writer.write(prefix + symbol + " ");
            }
        } else {
            for (Character symbol : symbols) {
                f(numbers, writer, buttons, prefix + symbol, deep + 1);
            }
        }
    }
}
