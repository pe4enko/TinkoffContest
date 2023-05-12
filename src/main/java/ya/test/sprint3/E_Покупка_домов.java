package ya.test.sprint3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * Тимофей решил купить несколько домов на знаменитом среди разработчиков Алгосском архипелаге. Он нашёл n объявлений о
 * продаже, где указана стоимость каждого дома в алгосских франках. А у Тимофея есть k франков. Помогите ему определить,
 * какое наибольшее количество домов на Алгосах он сможет приобрести за эти деньги.
 * <p>
 * Формат ввода В первой строке через пробел записаны натуральные числа n и k.
 * <p>
 * n — количество домов, которые рассматривает Тимофей, оно не превосходит 100000;
 * <p>
 * k — общий бюджет, не превосходит 10000;
 * <p>
 * В следующей строке через пробел записано n стоимостей домов. Каждое из чисел не превосходит 10000. Все стоимости —
 * натуральные числа.
 */
public class E_Покупка_домов {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int[] numberOfHousesAndHasMoney = readArray(reader);
            int hasMoney = numberOfHousesAndHasMoney[1];
            int[] prices = readArray(reader);

            Arrays.sort(prices);

            int canBuy = 0;
            int sum = 0;

            for (int price : prices) {
                sum += price;

                if (sum > hasMoney) {
                    break;
                }
                canBuy++;
            }

            writer.write(String.valueOf(canBuy));
            writer.flush();
        }
    }

    private static int[] readArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
