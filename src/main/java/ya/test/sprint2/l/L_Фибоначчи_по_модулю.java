package ya.test.sprint2.l;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

/**
 * У Тимофея было очень много стажёров, целых N (0 ≤ N ≤ 106) человек. Каждый стажёр хотел быть лучше своих
 * предшественников, поэтому i-й стажёр делал столько коммитов, сколько делали два предыдущих стажёра в сумме. Два
 * первых стажёра были менее инициативными — они сделали по одному коммиту.
 * <p>
 * Пусть Fi —– число коммитов, сделанных i-м стажёром (стажёры нумеруются с нуля). Первые два стажёра сделали по одному
 * коммиту: F0=F1=1. Для всех i≥ 2 выполнено Fi=Fi−1+Fi−2.
 * <p>
 * Определите, сколько кода напишет следующий стажёр –— найдите последние k цифр числа Fn.
 * <p>
 * <p>
 * Как найти k последних цифр
 * <p>
 * Чтобы вычислить k последних цифр некоторого числа x, достаточно взять остаток от его деления на число 10k. Эта
 * операция обозначается как x mod 10k. Узнайте, как записывается операция взятия остатка по модулю в вашем языке
 * программирования.
 * <p>
 * Также обратите внимание на возможное переполнение целочисленных типов, если в вашем языке такое случается.
 * <p>
 * Формат ввода В первой строке записаны через пробел два целых числа n (0 ≤ n ≤ 106) и k (1 ≤ k ≤ 8).
 * <p>
 * Формат вывода Выведите единственное число – последние k цифр числа Fn.
 * <p>
 * Если в искомом числе меньше k цифр, то выведите само число без ведущих нулей.
 */
public class L_Фибоначчи_по_модулю {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            //кол-во стажеров
            String[] config = reader.readLine().split("\\s");

            int n = Integer.parseInt(config[0]);
            int k = Integer.parseInt(config[1]);

            BigInteger fibN = fib(n);

            long div = (long) Math.pow(10, k);

            writer.write((fibN.mod(BigInteger.valueOf(div)) + ""));
            writer.flush();
        }
    }

    private static BigInteger fib(int n) {
        if (n == 1 || n == 0) {
            return ONE;
        }

        BigInteger fibNMinus2 = ONE;
        BigInteger fibNMinus1 = ONE;
        BigInteger fibN = ZERO;

        for (int i = 2; i <= n; i++) {
            fibN = fibNMinus2.add(fibNMinus1);
            fibNMinus2 = fibNMinus1;
            fibNMinus1 = fibN;
        }
        return fibN;
    }
}
