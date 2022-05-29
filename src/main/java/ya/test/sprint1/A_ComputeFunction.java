package ya.test.sprint1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * Вася делает тест по математике: вычисляет значение функций в различных точках. Стоит отличная погода, и друзья зовут
 * Васю гулять. Но мальчик решил сначала закончить тест и только после этого идти к друзьям. К сожалению, Вася пока не
 * умеет программировать. Зато вы умеете. Помогите Васе написать код функции, вычисляющей y = ax2 + bx + c. Напишите
 * программу, которая будет по коэффициентам a, b, c и числу x выводить значение функции в точке x.
 */
public class A_ComputeFunction {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            List<Integer> integers = readList(reader);
            int a = integers.get(0);
            int x = integers.get(1);
            int b = integers.get(2);
            int c = integers.get(3);

            var y = a * x * x + b * x + c;
            writer.write(String.valueOf(y));
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }
}
