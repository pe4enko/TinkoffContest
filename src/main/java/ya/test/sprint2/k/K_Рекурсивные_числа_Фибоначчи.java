package ya.test.sprint2.k;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * У Тимофея было n ( 0 ≤ n ≤ 3 2 ) стажёров. Каждый стажёр хотел быть лучше своих предшественников, поэтому i -й стажёр
 * делал столько коммитов, сколько делали два предыдущих стажёра в сумме. Два первых стажёра были менее инициативными —–
 * они сделали по одному коммиту. Пусть F i —– число коммитов, сделанных i -м стажёром (стажёры нумеруются с нуля).
 * Тогда выполняется следующее: F 0 = F 1 = 1 . Для всех i ≥ 2 выполнено F i = F i − 1 + F i − 2 . Определите, сколько
 * кода напишет следующий стажёр –— найдите F n . Решение должно быть реализовано рекурсивно. Формат ввода На вход
 * подаётся n — целое число в диапазоне от 0 до 3 2 . Формат вывода Нужно вывести F n .
 */
public class K_Рекурсивные_числа_Фибоначчи {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            //кол-во стажеров
            int n = readInt(reader);

            int fib = fib(n);

            writer.write(String.valueOf(fib));
            writer.flush();
        }
    }

    public static int fib(int n) {
        if (n == 0) {
            return 1;
        }

        if (n == 1) {
            return 1;
        }

        return fib(n - 1) + fib(n - 2);
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
