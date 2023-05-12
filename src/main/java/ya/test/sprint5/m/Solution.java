package ya.test.sprint5.m;

public class Solution {
    public static int siftUp(int[] heap, int idx) {
        if (idx == 1) {
            return idx;
        }
        int parentIndex = idx / 2;

        if (heap[parentIndex]< heap[idx]) {
            int tmp = heap[parentIndex];
            heap[parentIndex]=heap[idx];
            heap[idx] = tmp;

            return siftUp(heap, parentIndex);
        }

        return idx;
    }

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        int[] sample = {-1, 12, 6, 8, 3, 15, 7};
        assert siftUp(sample, 5) == 1;
    }
}