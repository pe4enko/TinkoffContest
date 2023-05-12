package ya.test.sprint5.a;

public class Solution {

    public static int treeSolution(Node head) {
        int max = Integer.MIN_VALUE;

        if (head.left != null) {
            max = Integer.max(max, treeSolution(head.left));
        }

        if (head.right != null) {
            max = Integer.max(max, treeSolution(head.right));
        }

        return Integer.max(max, head.value);
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
    }

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(-5);
        Node node3 = new Node(3);
        node3.left = node1;
        node3.right = node2;
        Node node4 = new Node(2);
        node4.left = node3;
        assert treeSolution(node4) == 3;
    }
}
