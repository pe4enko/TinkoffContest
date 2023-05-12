package ya.test.sprint3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * Чтобы выбрать самый лучший алгоритм для решения задачи, Гоша продолжил изучать разные сортировки. На очереди
 * сортировка пузырьком — https://ru.wikipedia.org/wiki/Сортировка_пузырьком
 * <p>
 * Её алгоритм следующий (сортируем по неубыванию):
 * <p>
 * На каждой итерации проходим по массиву, поочередно сравнивая пары соседних элементов. Если элемент на позиции i
 * больше элемента на позиции i + 1, меняем их местами. После первой итерации самый большой элемент всплывёт в конце
 * массива. Проходим по массиву, выполняя указанные действия до тех пор, пока на очередной итерации не окажется, что
 * обмены больше не нужны, то есть массив уже отсортирован. После не более чем n – 1 итераций выполнение алгоритма
 * заканчивается, так как на каждой итерации хотя бы один элемент оказывается на правильной позиции.
 * <p>
 * Помогите Гоше написать код алгоритма. Формат ввода В первой строке на вход подаётся натуральное число n — длина
 * массива, 2 ≤ n ≤ 1000. Во второй строке через пробел записано n целых чисел. Каждое из чисел по модулю не превосходит
 * 1000.
 * <p>
 * Обратите внимание, что считывать нужно только 2 строки: значение n и входной массив.
 * <p>
 * Формат вывода После каждого прохода по массиву, на котором какие-то элементы меняются местами, выводите его
 * промежуточное состояние. Таким образом, если сортировка завершена за k меняющих массив итераций, то надо вывести k
 * строк по n чисел в каждой — элементы массива после каждой из итераций. Если массив был изначально отсортирован, то
 * просто выведите его.
 */
public class J_Пузырёк {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            int[] whatToSort = readArray(reader);

            bubbleSort(whatToSort, writer);

            writer.flush();
        }
    }

    private static void bubbleSort(int[] whatToSort, BufferedWriter writer) throws IOException {
        boolean wasSwap;
        boolean globalWasSwap = false;

        do {
            wasSwap = false;

            for (int i = 0; i < whatToSort.length - 1; i++) {
                int e1 = whatToSort[i];
                int e2 = whatToSort[i + 1];

                if (e1 > e2) {
                    whatToSort[i + 1] = e1;
                    whatToSort[i] = e2;
                    wasSwap = true;
                    globalWasSwap = true;
                }
            }
            if (wasSwap) {
                print(whatToSort, writer);
            }

        } while (wasSwap);

        if (!globalWasSwap) {
            print(whatToSort, writer);
        }
    }

    private static void print(int[] whatToPrint, BufferedWriter writer) throws IOException {
        for (int i : whatToPrint) {
            writer.write(i + " ");
        }
        writer.write("\n");
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
