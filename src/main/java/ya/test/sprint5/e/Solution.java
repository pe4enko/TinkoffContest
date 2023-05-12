package ya.test.sprint5.e;

/**
 * https://ru.stackoverflow.com/questions/771612/%D0%BF%D1%80%D0%BE%D0%B2%D0%B5%D1%80%D0%BA%D0%B0-%D0%B4%D0%B2%D0%BE%D0%B8%D1%87%D0%BD%D0%BE%D0%B3%D0%BE-%D0%B4%D0%B5%D1%80%D0%B5%D0%B2%D0%B0-%D0%BF%D0%BE%D0%B8%D1%81%D0%BA%D0%B0-%D0%BD%D0%B0-%D0%BA%D0%BE%D1%80%D1%80%D0%B5%D0%BA%D1%82%D0%BD%D0%BE%D1%81%D1%82%D1%8C
 */
public class Solution {

    public static boolean treeSolution(Node head) {
        return check(head, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean check(Node node, int minValue, int maxValue) {
        if (node == null) {
            return true;
        }

        if (!(node.value > minValue && node.value < maxValue)) {
            return false;
        }

        return check(node.left, minValue, node.value)
                && check(node.right, node.value, maxValue);
    }

    public static void main(String[] args) {
        test();
    }

    /**
     * Comment it before submitting
     */
    private static class Node {

        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
    //**/


    private static void test() {
        Node node1 = new Node(1, null, null);
        Node node2 = new Node(4, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(8, null, null);
        Node node5 = new Node(5, node3, node4);
        assert treeSolution(node5);
        node2.value = 5;
        assert !treeSolution(node5);
    }
}
