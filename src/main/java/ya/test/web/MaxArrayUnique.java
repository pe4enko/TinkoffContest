package ya.test.web;

import static java.lang.Math.max;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Максимальная подпоследовательность с уникальными элементами
 * Задача из
 * Вебинар "Открытое алгоритмическое собеседование! | Яндекс.Практикум
 * https://www.youtube.com/watch?v=aYuAd-IDigc
 */
public class MaxArrayUnique {

    public static void main(String[] args) {
//        int[] arr = {1, 2, 3, 3};
//        int[] arr = {1, 2, 3, 4, 1, 2, 3, 4};
//        int[] arr = {1, 2, 3, 2, 2, 6, 7, 1};
//        int[] arr = {1, 2, 3, 4, 5, 5, 1};
        int[] arr = {5, 9, 1, 2, 3, 9, 7, 8, 5, 4, 6, 0, 9, 5, 3};
//        int[] arr = {1, 2, 3, 3, 2, 1};
//        int[] arr = {1, 2, 3, 4, 5, 2, 2};

        int max = findMax(arr);
        int max1 = getMaxLen(arr);
        int max2 = getMaxLenMy(arr);

        System.out.println("my " + max);
        System.out.println("il " + max1);
        System.out.println("ilMy " + max2);
    }

    public static int findMax(int[] arr) {
        Set<Integer> cache = new HashSet<>();
        int max = 0;

        for (int i = 0; i < arr.length; i++) {
            int a = arr[i];
            cache.add(a);
            max = max(max, cache.size());

            for (int j = i + 1; j < arr.length; j++) {
                int b = arr[j];

                boolean add = cache.add(b);
                max = max(max, cache.size());
                if (!add) {
                    cache.clear();
                    cache.add(b);
                }
            }
            cache.clear();
        }
        return max;
    }

    public static int getMaxLen(int[] arr) {
        Map<Integer, Integer> num2pos = new HashMap<>();

        /** Позиция в которой встретилась в прошлый раз*/
        int prev = 0;
        int len = 0;

        for (int i = 0; i < arr.length; i++) {
            int arrI = arr[i];
            Integer npc = num2pos.get(arrI);
            prev = max(prev, npc == null ? 0 : npc);
            len = max(len, i - prev + 1);
            num2pos.put(arrI, i + 1);
        }
        return len;
    }

    public static int getMaxLenMy(int[] arr) {
        /* В какой позиции уже встречалось данное число <Число, Позиция>*/
        HashMap<Integer, Integer> num2pos = new HashMap<>();
        /* Максимальная длина */
        int len = 0;
        /* Текущая позиция от которой мы считаем длину*/
        int prev  = 0;

        for (int i = 0; i < arr.length; i++) {
            int arrI = arr[i];

            Integer previousPositionOfCurrentDigitFromArr = num2pos.get(arrI);
            //1. Такая цифра еще не встречалась -> тогда prev изменять не нужно
            //2. Если такая цифра встречалась -> тогда prev = новой позиции.
            if (previousPositionOfCurrentDigitFromArr != null) {
                prev = max(prev, previousPositionOfCurrentDigitFromArr + 1);
            }

            len = max(len, i + 1 - prev);
            //Запоминаем, что цифра из масссива arr = arrI встрелось в позиции i
            num2pos.put(arrI, i);
        }

        return len;
    }

    //i    = 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14
    //arr  = 5 9 1 2 3 9 7 8 5 4  6  0  9  5  3
    //pp   = _ _ _ _ _ 1 _ _ 0
    //prev = 0 0 0 0 0 2 2 2
    //len  = 1 2 3 4 5 5 5 6
}

//     i = 0 1 2 3 4 5 6
//   arr = 1 2 3 1 2 3 4

//     i = 0 1 2 3 4 5 6
//  arrI = 1 2 3 1 2 3 4
//   npc = 0 0 0 1 2 3 0
//  prev = 0 0 0 1 2 3 3
//   len = 1 2 3 3 3 3 4

//num2pos
//1 -> 1 4
//2 -> 2 5
//3 -> 3 6
//4 -> 7














