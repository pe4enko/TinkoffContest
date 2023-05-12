package yandex.y1;

public class MinMultiply {

    public static void main(String[] args) {
//        int[] arr = {1, 2, 3, 4, 5};
//        int[] arr = {-1, -2, -3, -4, -5};
        int[] arr = {-1, 2, 3, 4, 5};

        Integer min1 = arr[0];
        Integer min2 = arr[1];
        Integer max1 = arr[0];
        Integer max2 = arr[1];

        for (int i = 2; i < arr.length; i++) {
            if (max1 <= arr[i]) {
                max2 = max1;
                max1 = arr[i];
            }

            if (min1 >= arr[i]) {
                min2 = min1;
                min1 = arr[i];
            }
        }

        System.out.println(min1);
        System.out.println(min2);
        System.out.println(max1);
        System.out.println(max2);

        if (min1 < 0 && max1 > 0) {
            System.out.println("Минимальное произведение: " + min1 * max1);
        } else if (max1 < 0) {
            System.out.println("Минимальное произведение: " + max1 * max2);
        } else {
            System.out.println("Минимальное произведение: " + min1 * min2);
        }
    }
}
