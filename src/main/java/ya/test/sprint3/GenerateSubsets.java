package ya.test.sprint3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * !!! Это не перестановки это подмножества
 * <p/>
 * Дан упорядоченный по возрастанию массив с числами от $0$ до $n-1$, требуется
 * <p>
 * сгенерировать все его подмножества.
 * <p>
 * Заметим, что количество подмножеств такого множества равно в точности $2^n$. Если каждое подмножество представить в
 * виде массива индексов, где $0$ означает, что элемент не входит в множество, а $1$ — что входит, тогда генерирование
 * всех таких массивов будет являться генерированием всех подмножеств.
 * <p>
 * <p>
 * Если рассмотреть побитовое представление чисел от 0 до $2^n - 1$, то они будут задавать искомые подмножества. То есть
 * для решения задачи необходимо реализовать прибавление единицы к двоичному числу. Начинаем со всех нулей и заканчиваем
 * массивом, в котором одни единицы.
 */
public class GenerateSubsets {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            generate(n, writer);

            writer.flush();
        }
    }

    private static void generate(int n, BufferedWriter writer) throws IOException {
        //размерность на 1 больше для того чтобы было удобно выходить из цикла
        int[] binary = new int[n + 1];
        int[] originalSubset = IntStream.rangeClosed(1, n).toArray();

        while (binary[0] == 0) {
            int ind = n;

            // ищем самый правый ноль
            while (binary[ind] == 1) {
                ind--;
            }

            // заменяем на 1
            binary[ind] = 1;

            // на все остальное пишем нули
            Arrays.fill(binary, ind + 1, n + 1, 0);

            print(binary, originalSubset, writer);
        }
    }

    private static void print(int[] printMatrix, int[] whatToPrint, BufferedWriter writer) throws IOException {
        for (int i = 1; i < printMatrix.length; i++) {
            int matrix = printMatrix[i];
            if (matrix > 0) {
                writer.write(String.valueOf(whatToPrint[i-1]));
            }
        }
        writer.write("\n");
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
