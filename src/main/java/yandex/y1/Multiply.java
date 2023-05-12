package yandex.y1;

import java.util.Arrays;

/**
 Дана последовательность целых чисел. Необходимо найти минимально возможное произведение пары элементов последовательности.
 Например, для последовательности чисел 9 4 2 5 3 ответ будет 6.
 */
public class Multiply {

    //1 2 3 4 5
    //-1 2 3 4 5
    //-5 -4 -3 -2 -1

    public static void main(String[] args) {

    }

    public static long mul(int[] ints) {
        if (ints.length < 2) {
            throw new IllegalArgumentException();
        }

        boolean hasNegative = false;
        boolean hasPositive = false;

//        Arrays.sort(ints);

//        hasNegative = ints[0] < 0;
//        hasPositive = ints[ints.length -1] > 0;

        Integer min1 = ints[0];
        Integer min2 = ints[0];

        Integer max1;
        Integer max2;

        for (int i : ints) {
            if (i < 0) {
                hasNegative = true;
            } else {
                hasPositive = true;
            }

            //min1 = 1
            //min2 = 1
            //i = 1 2
            //1 2 3 4 5
            min1 = Math.min(i, min2);
            min2 = Math.min(i, min2);

//            if (hasNegative && hasPositive) {
//                break;
//            }
        }



        if (hasPositive && !hasNegative) {
            return (long) ints[0] * (long) ints[1];
        } else if (hasNegative && hasPositive) {
            return (long)ints[0] * (long)ints[ints.length - 1];
        } else {
            return (long) ints[ints.length - 1] * (long) ints[ints.length - 2];
        }
    }
}
