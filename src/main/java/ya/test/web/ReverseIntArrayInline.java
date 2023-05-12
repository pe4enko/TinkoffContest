package ya.test.web;

import java.util.Arrays;

public class ReverseIntArrayInline {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6};

        reverse(arr);

        System.out.println(Arrays.toString(arr));
    }

    public static void reverse(int[] arr) {
        int l = 0;
        int r = arr.length - 1;

        while (l < r) {
            int tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
            l++;
            r--;
        }
    }
}
