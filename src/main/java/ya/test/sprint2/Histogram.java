package ya.test.sprint2;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Задача про гистограмму
 */
public class Histogram {

    public static void main(String[] args) throws IOException {
        LinkedList<HeightAndIndex> stack = new LinkedList<>();

        int[] heights = {2, 7, 6, 9, 7, 5, 7, 3, 5};
        int[] left = new int[heights.length];

        for (int k = 0; k < heights.length; k++) {
            int h = heights[k];

            do {
                HeightAndIndex lastElementInStack = stack.peek();
                if (lastElementInStack == null) {
                    //для элемента 0 у нас граница в -1 элементе с высотой 0
                    stack.push(new HeightAndIndex(-1, 0));
                    left[k] = -1;
                    stack.push(new HeightAndIndex(k, h));
                    break;
                } else if (lastElementInStack.height >= h) {
                    stack.pop();
                } else {
                    left[k] = lastElementInStack.index;
                    stack.push(new HeightAndIndex(k, h));
                    break;
                }
            } while (!stack.isEmpty());
        }

        stack.clear();

        int[] right = new int[heights.length];

        for (int k = heights.length - 1; k >= 0; k--) {
            int h = heights[k];

            do {
                HeightAndIndex lastElementInStack = stack.peek();
                if (lastElementInStack == null) {
                    //для элемента 0 у нас граница в -1 элементе с высотой 0
                    stack.push(new HeightAndIndex(k + 1, 0));
                    right[k] = k + 1;
                    stack.push(new HeightAndIndex(k, h));
                    break;
                } else if (lastElementInStack.height >= h) {
                    stack.pop();
                } else {
                    right[k] = lastElementInStack.index;
                    stack.push(new HeightAndIndex(k, h));
                    break;
                }
            } while (!stack.isEmpty());
        }

        int maxS = 0;
        int indexOfHeightWithMaxS = -1;
        for (int i = 0; i < heights.length; i++) {
            int hi = heights[i];
            int li = left[i];
            int ri = right[i];

            int s = hi * (ri - li - 1);

            if (s > maxS) {
                maxS = s;
                indexOfHeightWithMaxS = i;
            }
        }

        System.out.println("Максимальная площадь: " + maxS + "\nИндекс высоты: " + indexOfHeightWithMaxS + "\nВысота: "
                + heights[indexOfHeightWithMaxS]);
    }

    private static class HeightAndIndex {

        private final Integer index;
        private final Integer height;

        public HeightAndIndex(Integer index, Integer height) {
            this.index = index;
            this.height = height;
        }

        @Override
        public String toString() {
            return "HeightAndIndex{" +
                    "index=" + index +
                    ", height=" + height +
                    '}';
        }
    }
}
