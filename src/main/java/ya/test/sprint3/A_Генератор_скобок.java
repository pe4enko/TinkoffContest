package ya.test.sprint3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Рита по поручению Тимофея наводит порядок в правильных скобочных последовательностях (ПСП), состоящих только из
 * круглых скобок (). Для этого ей надо сгенерировать все ПСП длины 2n в алфавитном порядке —– алфавит состоит из ( и )
 * и открывающая скобка идёт раньше закрывающей.
 * <p>
 * Помогите Рите —– напишите программу, которая по заданному n выведет все ПСП в нужном порядке.
 * <p>
 * Рассмотрим второй пример. Надо вывести ПСП из четырёх символов. Таких всего две:
 * <p>
 * (()) ()() (()) идёт раньше ()(), так как первый символ у них одинаковый, а на второй позиции у первой ПСП стоит (,
 * который идёт раньше ). Формат ввода На вход функция принимает n — целое число от 0 до 10.
 * <p>
 * Формат вывода Функция должна напечатать все возможные скобочные последовательности заданной длины в алфавитном
 * (лексикографическом) порядке.
 */
public class A_Генератор_скобок {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);

            genParenthesis(n, "", 0, 0, writer);

            writer.flush();
        }
    }


    private static void genParenthesis(int n, String prefix, int leftCount, int rightcount, BufferedWriter writer)
            throws IOException {
        if (leftCount == n && rightcount == n) {
            writer.write(prefix + "\n");
            return;
        }

        if (leftCount < n) {
            genParenthesis(n, prefix + "(", leftCount + 1, rightcount, writer);
        }

        if (rightcount < leftCount) {
            genParenthesis(n, prefix + ")", leftCount, rightcount + 1, writer);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

}
