package tinkoff;

import static java.lang.System.out;

/**
 * Даны три неубывающих массива чисел. Найти число, которое присутствует во всех трех массивах.
 *
 */
public class Arrays3 {

    public static void main(String[] args) {
        int[] ar = {1, 2, 4, 5};
        int[] br = {3, 3, 4};
        int[] cr = {2, 3, 4, 5, 6};

        int ai = 0;
        int bi = 0;
        int ci = 0;

        int a = ar[0];
        int b = br[0];
        int c = cr[0];

        boolean found = false;

        while (true) {
            if (b > a) {
                ai++;
                if (ai >= ar.length) {
                    break;
                }
                a = ar[ai];
                continue;
            }

            if (b < a) {
                bi++;

                if (bi >= br.length) {
                    break;
                }

                b = br[bi];
                continue;
            }

            if (c > b) {
                bi++;

                if (bi >= br.length) {
                    break;
                }

                b = br[bi];
                continue;
            }

            if (c < b) {
                ci++;

                if (ci >= cr.length) {
                    break;
                }

                c = cr[ci];
                continue;
            }

            found = true;
            break;
        }

        if (found) {
            out.println("Нашли общее число: " + a);
        } else {
            out.println("НЕ Нашил общее число.");
        }

//[1,2,4,5]
//[3,3,4]
//[2,3,3,5,6]
//
//a = 1 2 4
//b = 3 3 4
//c = 2 3 3 5

    }

}
