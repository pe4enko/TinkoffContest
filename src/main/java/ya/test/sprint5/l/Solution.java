package ya.test.sprint5.l;

public class Solution {
    public static int siftDown(int[] heap, int idx) {
        int leftIdx = 2 * idx;
        int rightIdx = leftIdx + 1;

        if (leftIdx >= heap.length) {
            return idx;
        }

        int indexLargest;
        if (rightIdx < heap.length && heap[leftIdx] <  heap[rightIdx]) {
            indexLargest = rightIdx;
        } else {
            indexLargest = leftIdx;
        }

        if (heap[idx] < heap[indexLargest]) {
            int tmp = heap[idx];
            heap[idx] = heap[indexLargest];
            heap[indexLargest] = tmp;
            return siftDown(heap, indexLargest);
        }

        return idx;
    }

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        {
            int[] sample = {-1, 12, 1, 8, 3, 4, 7};
            assert siftDown(sample, 2) == 5;
        }

        {
            int[] sample = {-1, 4, 1, 8, 3, 4, 7};
            assert siftDown(sample, 1) == 6;
        }

        {
            int[] sample = {-1, 12, 1, 1, 3, 4, 7};
            assert siftDown(sample, 3) == 6;
        }

        {
            int[] sample = {-1, 12, 1, 8, 3, 4, 7};
            assert siftDown(sample, 1) == 1;
        }

        {
            int[] sample = {-1, 12};
            assert siftDown(sample, 1) == 1;
        }
    }
}