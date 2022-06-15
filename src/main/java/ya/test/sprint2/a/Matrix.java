package ya.test.sprint2.a;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * Алла получила задание, связанное с мониторингом работы различных серверов. Требуется понять, сколько времени
 * обрабатываются определённые запросы на конкретных серверах. Эту информацию нужно хранить в матрице, где номер столбца
 * соответствуют идентификатору запроса, а номер строки — идентификатору сервера. Алла перепутала строки и столбцы
 * местами. С каждым бывает. Помогите ей исправить баг.
 * <p>
 * Есть матрица размера m × n. Нужно написать функцию, которая её транспонирует.
 * <p>
 * Транспонированная матрица получается из исходной заменой строк на столбцы.
 * <p>
 * Например, для матрицы А (слева) транспонированной будет следующая матрица (справа):
 * <p>
 * <p>
 * <p>
 * Формат ввода В первой строке задано число n — количество строк матрицы. Во второй строке задано m — число столбцов, m
 * и n не превосходят 1000. В следующих n строках задана матрица. Числа в ней не превосходят по модулю 1000.
 * <p>
 * Формат вывода Напечатайте транспонированную матрицу в том же формате, который задан во входных данных. Каждая строка
 * матрицы выводится на отдельной строке, элементы разделяются пробелами.
 */
public class Matrix {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

//            n — количество строк матрицы.
            int n = readInt(reader);
//            m — число столбцов
            int m = readInt(reader);

            String[][] matrix = readMatrix(reader, n);

            for (int j = 0; j <= m - 1; j++) {
                for (int i = 0; i <= n - 1; i++) {
                    writer.write(matrix[i][j] + " ");
                }
                writer.write("\n");
            }

            writer.flush();
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static String[][] readMatrix(BufferedReader reader, int times) throws IOException {
        String[][] matrix = new String[times][times];

        for (int i = 0; i < times; i++) {
            matrix[i] = reader.readLine().split("\\s");
        }

        return matrix;
    }
}
