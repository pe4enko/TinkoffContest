package ya.test.sprint1final;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * https://contest.yandex.ru/contest/22450/run-report/68684863/
 * https://contest.yandex.ru/contest/22450/run-report/68657509/
 * <p>
 * Улица, на которой хочет жить Тимофей, имеет длину n, то есть состоит из n одинаковых идущих подряд участков. На
 * каждом участке либо уже построен дом, либо участок пустой. Тимофей ищет место для строительства своего дома. Он очень
 * общителен и не хочет жить далеко от других людей, живущих на этой улице.
 * <p>
 * Чтобы оптимально выбрать место для строительства, Тимофей хочет для каждого участка знать расстояние до ближайшего
 * пустого участка. (Для пустого участка эта величина будет равна нулю –— расстояние до самого себя).
 * <p>
 * Ваша задача –— помочь Тимофею посчитать искомые расстояния. Для этого у вас есть карта улицы. Дома в городе Тимофея
 * нумеровались в том порядке, в котором строились, поэтому их номера на карте никак не упорядочены. Пустые участки
 * обозначены нулями.
 * <p>
 * Формат ввода В первой строке дана длина улицы —– n (1 ≤ n ≤ 106). В следующей строке записаны n целых неотрицательных
 * чисел — номера домов и обозначения пустых участков на карте (нули). Гарантируется, что в последовательности есть хотя
 * бы один ноль. Номера домов (положительные числа) уникальны и не превосходят 109.
 * <p>
 * Формат вывода Для каждого из участков выведите расстояние до ближайшего нуля. Числа выводите в одну строку, разделяя
 * их пробелами.
 * <p>
 * Ввод: 5 0 1 4 9 0
 * <p>
 * Вывод: 0 1 2 1 0
 * <p>
 * Ввод: 6 0 7 9 4 8 20
 * <p>
 * Вывод: 0 1 2 3 4 5
 */
public class A_Ближайший_ноль {

    final static int REMEMBERED_HOUSE_POSITION_IS_ABSENT = -1;


    public static void nearestZero(int[] homeNumbers) {
        int distance = homeNumbers.length;

        for (int i = 0; i < homeNumbers.length; i++) {
            int currentCheckNumber = homeNumbers[i];

            if (hasHouse(currentCheckNumber)) {
                homeNumbers[i] = distance++;
            } else {
                distance = 1;
            }
        }

        distance = homeNumbers.length;

        for (int i = homeNumbers.length - 1; i >= 0; i--) {
            int currentCheckNumber = homeNumbers[i];

            if (hasHouse(currentCheckNumber)) {
                homeNumbers[i] = Math.min(distance, currentCheckNumber);
                distance++;
            } else {
                distance = 1;
            }
        }
    }

    public static void nearestZeroVeryComplex(int[] homeNumbers) {
        int rememberedHousePosition = REMEMBERED_HOUSE_POSITION_IS_ABSENT;

        for (int i = 0; i < homeNumbers.length; i++) {
            int currentCheckNumber = homeNumbers[i];

            //Если в проверяемой позиции имеется дом и до этого мы не запоминали номер дома, то запомним
            if (hasHouse(currentCheckNumber)) {
                if (rememberedPositionIsAbsent(rememberedHousePosition)) {
                    rememberedHousePosition = i;
                }
            } else {
                //Если в проверяемой позиции НЕТ дома, у нас есть запомненный дом
                if (rememberedHousePosition != REMEMBERED_HOUSE_POSITION_IS_ABSENT) {
                    int inc = 0;
                    for (int j = rememberedHousePosition; j < i; j++) {
                        homeNumbers[j] = i - rememberedHousePosition - inc++;
                    }
                    rememberedHousePosition = REMEMBERED_HOUSE_POSITION_IS_ABSENT;
                }
                //Если в проверяемой позиции НЕТ дома, и у нас НЕТ запомненного дома. То ничего не делаем.
            }
        }

        rememberedHousePosition = REMEMBERED_HOUSE_POSITION_IS_ABSENT;

        for (int i = homeNumbers.length - 1; i >= 0; i--) {
            int currentCheckNumber = homeNumbers[i];

            //Если в проверяемой позиции имеется дом и до этого мы не запоминали номер дома, то запомним
            if (hasHouse(currentCheckNumber)) {
                if (rememberedPositionIsAbsent(rememberedHousePosition)) {
                    rememberedHousePosition = i;
                }
            } else {
                //Если в проверяемой позиции НЕТ дома, у нас есть запомненный дом
                if (rememberedHousePosition != REMEMBERED_HOUSE_POSITION_IS_ABSENT) {
                    int inc = 0;
                    for (int j = rememberedHousePosition; j >= i; j--) {
                        int diff = rememberedHousePosition - i - inc++;
                        if (homeNumbers[j] > diff) {
                            homeNumbers[j] = diff;
                        }
                    }
                    rememberedHousePosition = REMEMBERED_HOUSE_POSITION_IS_ABSENT;
                }
                //Если в проверяемой позиции НЕТ дома, и у нас НЕТ запомненного дома, то ничего не делаем
            }
        }
    }

    /**
     * Решение от наставника
     */
    private static int[] closerZero(int[] houses) {
        int[] distances = new int[houses.length];
        int distance = houses.length;
        // слева направо
        for (int i = 0; i < houses.length; i++) {
            if (houses[i] == 0) {
                distance = 0;
            }
            distances[i] = distance;
            distance++;
        }
        // справа налево
        distance = houses.length;
        for (int i = houses.length - 1; i >= 0; i--) {
            if (houses[i] == 0) {
                distance = 0;
            }
            distances[i] = Math.min(distance, distances[i]);
            distance++;
        }
        return distances;
    }

    private static boolean rememberedPositionIsAbsent(int rememberedHousePosition) {
        return rememberedHousePosition == REMEMBERED_HOUSE_POSITION_IS_ABSENT;
    }

    private static boolean hasHouse(int houseNumber) {
        return houseNumber > 0;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            int[] homeNumbers = readIntArray(reader);

            nearestZero(homeNumbers);

            for (int elem : homeNumbers) {
                writer.write(elem + " ");
            }

            writer.flush();
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readIntArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
