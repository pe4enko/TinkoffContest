//package ya.test.sprint3.k;

import java.util.Arrays;

public class Solution {

    public static int[] merge(int[] arr, int left, int mid, int right) {
        int[] mergedArray = new int[right - left];

        int l = left;
        int r = mid;

        int indexToPut = 0;

        while (l < mid && r < right) {
            int leftValue = arr[l];
            int rightValue = arr[r];

            if (leftValue <= rightValue) {
                mergedArray[indexToPut] = leftValue;
                l++;
            } else {
                mergedArray[indexToPut] = rightValue;
                r++;
            }
            indexToPut++;
        }

        while (l < mid) {
            mergedArray[indexToPut] = arr[l];
            l++;
            indexToPut++;
        }

        while (r < right) {
            mergedArray[indexToPut] = arr[r];
            r++;
            indexToPut++;
        }

        return mergedArray;
    }

    public static void merge_sort(int[] arr, int left, int right) {
        if (right - left <= 1) {
            return;
        }

        int mid = left + (right - left) / 2;
        merge_sort(arr, left, mid);
        merge_sort(arr, mid, right);

        int[] merged = merge(arr, left, mid, right);
        System.arraycopy(merged, 0, arr, left, right - left);
    }

    public static void main(String[] args) {
        {
            int[] a = {1, 4, 9, 2, 10, 11};
            int[] b = merge(a, 0, 3, 6);
            int[] expected = {1, 2, 4, 9, 10, 11};

            assert Arrays.equals(b, expected);
        }

        {
            int[] a = {3, 4, 5, 2, 3};
            int[] b = merge(a, 0, 3, 5);
            int[] expected = {2, 3, 3, 4, 5};

            assert Arrays.equals(b, expected);
        }

        {
            int[] c = {1, 4, 2, 10, 1, 2};
            merge_sort(c, 0, 6);
            int[] expected2 = {1, 1, 2, 2, 4, 10};
            assert Arrays.equals(c, expected2);
        }

        {
            int[] c = {4, 3, 2, 1};
            merge_sort(c, 0, 4);
            int[] expected2 = {1, 2, 3, 4};
            assert Arrays.equals(c, expected2);
        }
    }
}