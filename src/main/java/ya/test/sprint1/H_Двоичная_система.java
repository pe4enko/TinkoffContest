package ya.test.sprint1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Тимофей спросил у Гоши, умеет ли тот работать с числами в двоичной системе счисления. Он ответил, что проходил это на
 * одной из первых лекций по информатике. Тимофей предложил Гоше решить задачку. Два числа записаны в двоичной системе
 * счисления. Нужно вывести их сумму, также в двоичной системе. Встроенную в язык программирования возможность сложения
 * двоичных чисел применять нельзя.
 * <p>
 * Решение должно работать за O(N), где N –— количество разрядов максимального числа на входе.
 * <p>
 * Формат ввода Два числа в двоичной системе счисления, каждое на отдельной строке. Длина каждого числа не превосходит
 * 10 000 символов.
 * <p>
 * Формат вывода Одно число в двоичной системе счисления.
 */
public class H_Двоичная_система {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String first = reader.readLine();
            String second = reader.readLine();

            int firstLenght = first.length();
            int secondLenght = second.length();

            StringBuilder sb = new StringBuilder();

            int rememberValue = 0;

            for (int i = 0; i < Math.max(firstLenght, secondLenght); i++) {
                int f = getNextInt(first, firstLenght - 1 - i);
                int s = getNextInt(second, secondLenght - 1 - i);

                //тут можно по другому, если n > 1 то можно вычесть 2 это и будет добавляемое значение, при этом
                // запоминаем 1, иначе просто дописываем полученные n и запоминаем 0
                int n = f + s + rememberValue;
                if (n == 3) {
                    sb.append(1);
                    rememberValue = 1;
                } else if (n == 2) {
                    sb.append(0);
                    rememberValue = 1;
                } else if (n == 1) {
                    sb.append(1);
                    rememberValue = 0;
                } else if (n == 0) {
                    sb.append(0);
                    rememberValue = 0;
                }
            }

            if (rememberValue > 0) {
                sb.append(rememberValue);
            }

            writer.write(sb.reverse().toString());
            writer.flush();
        }
    }

    private static int getNextInt(String from, int i) {
        return i < 0 ? 0 : Character.getNumericValue(from.charAt(i));
    }
}
