package ya.test.sprint5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Ребятам стало интересно, сколько может быть различных деревьев поиска, содержащих в своих узлах все уникальные числа
 * от 1 до n. Помогите им найти ответ на этот вопрос.
 * <p>
 * Формат ввода В единственной строке задано число n. Оно не превосходит 20.
 * <p>
 * Формат вывода Нужно вывести число, равное количеству различных деревьев поиска, в узлах которых могут быть размещены
 * числа от 1 до n включительно.
 * <p>
 * <p>
 * Природа двоичного дерева поиска такова: левый узел меньше, чем родительский узел, а правый узел больше, чем
 * родительский узел. Предполагая, что 1 ... n - это число двоичных деревьев поиска, состоящих из узлов, равно G (n),
 * когда корневой узел равен 1, количество левых узлов поддерева равно 0, а правых узлов поддерева Число равно n-1,
 * общее количество равно G (0) * G (n-1), когда корневому узлу равно 2, количество левых узлов поддерева равно 1,
 * количество правых узлов поддерева равно n-2, и общее количество равно G (1) * G (n-2); ... и т. Д., Когда корневым
 * узлом является n, общее число составляет G (n-1) * G (0). Так что есть
 * <p>
 * G(n) = G(0)*G(n-1) + G(1)*G(n-2) + G(2)*G(n-3) + … +G(n-1)*G(0)
 */
public class I_Разные_деревья_поиска {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);

//            writer.write(String.valueOf(numTrees(n)));
            writer.write(String.valueOf(catalan(n)));

            writer.flush();
        }
    }

    public static int numTrees(int n) {
        int nums[] = new int[n + 1];
        nums[0] = 1;
        nums[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                nums[i] += nums[j] * nums[i - j - 1];
            }
        }
        return nums[n];
    }

    public static int catalan(int n) {
        double catalan = 1;

        for (int i = 1; i <= n; i++) {
            catalan = ((2 * (2 * i - 1)) / (double)(i + 1)) * catalan;
        }

        return (int)catalan;
    }


    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
