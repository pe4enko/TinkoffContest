package ya.test.sprint5.b;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static boolean treeSolution(Node head) {

        List<Integer> deeps = new ArrayList<>();

        int deep = 0;
//        System.out.println(head.value);

        if (head.left != null) {
            treeSolution2(head.left, deep + 1, deeps);
        }

        if (head.right != null) {
            treeSolution2(head.right, deep + 1, deeps);
        }

        if (head.left == null || head.right == null) {
            deeps.add(deep);
        }

        Integer min = deeps.stream()
                .min(Integer::compareTo).orElse(0);

        Integer max = deeps.stream()
                .max(Integer::compareTo).orElse(0);

//        System.out.println("Min:" + min);
//        System.out.println("Max:" + max);

        if (max - min > 1) {
            return false;
        }

        return true;
    }

    public static boolean treeSolution2(Node head, int deep, List<Integer> deeps) {

//        System.out.println(head.value);

        if (head.left != null) {
            treeSolution2(head.left, deep + 1, deeps);
        }

        if (head.right != null) {
            treeSolution2(head.right, deep + 1, deeps);
        }

        deeps.add(deep);
//        System.out.println("deep: " + deep);

        return true;
    }

/*
    private Result isBalancedRecursive(Node tree, int depth) {
        if (tree == null) {
            return new Result(true, -1);
        }

        Result leftSubtreeResult = isBalancedRecursive(tree.left, depth + 1);
        Result rightSubtreeResult = isBalancedRecursive(tree.right, depth + 1);

        boolean isBalanced = Math.abs(leftSubtreeResult.height - rightSubtreeResult.height) <= 1;
        boolean subtreesAreBalanced = leftSubtreeResult.isBalanced && rightSubtreeResult.isBalanced;
        int height = Math.max(leftSubtreeResult.height, rightSubtreeResult.height) + 1;

        return new Result(isBalanced && subtreesAreBalanced, height);
    }
*/


    public static void main(String[] args) {
        test();
    }

    /**
     * Comment it before submitting*/
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

    private class Result {
        private boolean isBalanced;
        private int height;

        private Result(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }
    //**/

    private static void test() {

        {
            Node node1 = new Node(1);
            Node node2 = new Node(-5);
            Node node3 = new Node(3);
            node3.left = node1;
            node3.right = node2;
            Node node4 = new Node(10);
            Node node5 = new Node(2);
            node5.left = node3;
            node5.right = node4;

            assert treeSolution(node5);
        }

        {

            Node node2 = new Node(2);
            Node node4 = new Node(1);
            Node node5 = new Node(0);
            node5.right = node4;
            node4.right = node2;

            assert !treeSolution(node5);

        }

        {

            Node node3 = new Node(1);
            Node node1 = new Node(4);
            Node node2 = new Node(3);
            Node node4 = new Node(2);
            Node node5 = new Node(0);
            node5.right = node4;
            node5.left = node3;
            node4.right = node1;
            node4.left = node2;

            assert treeSolution(node5);
        }

    }


}
