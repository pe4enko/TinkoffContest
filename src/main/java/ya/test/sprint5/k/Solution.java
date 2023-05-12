package ya.test.sprint5.k;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

//todo: не до конца разобрался, сложность алгоритма не соответствует
public class Solution {

    public static void printRange(Node root, int L, int R, BufferedWriter writer) throws IOException {
//        функция print_LMR(vertex)
//                если vertex.left != None тогда print_LMR(vertex.left)
//                print(vertex.value)
//        если vertex.right != None тогда print_LMR(vertex.right)

//        Если корень дерева меньше искомого элемента 1500, поиск производится в правом поддереве.
//        Если корень дерева больше или равен искомому элементу 1500, мы рекурсивно повторяем процедуру поиска в левой половине.
//        Если в левой половине элементов не нашлось, значит, корень дерева является первым элементом, который имеет стоимость больше либо равную 1500.

        writer.write("Спуск: " + root.getValue() + "\n");

        if (root.getValue() < L && root.getRight() != null) {
            printRange(root.getRight(), L, R, writer);
        } else if (root.getValue() >= R && root.getLeft() != null) {
            printRange(root.getLeft(), L, R, writer);
        } else {
            if (root.getLeft() != null) {
                printRange(root.getLeft(), L, R, writer);
            }

            writer.write(root.getValue() + "\n");

            if (root.getRight() != null) {
                printRange(root.getRight(), L, R, writer);
            }
        }

        writer.write("Выход: " + root.getValue() + "\n");


/*
        if (root.getLeft() != null && root.getValue() >= L) {
            printRange(root.getLeft(), L, R, writer);
        }

//        if (root.getValue() >= L && root.getValue() <= R) {
            writer.write(root.getValue() + "\n");
//        }

        if (root.getRight() != null && root.getValue() < L) {
            printRange(root.getRight(), L, R, writer);
        }

        writer.write("конец" + root.getValue() + "\n");
*/
//        if (root.getLeft() != null) {
//            printRange(root.getLeft(), L, R, writer);
//        }
//
//        if (root.getValue() >= L && root.getValue() <= R) {
//            writer.write(root.getValue() + "\n");
//        }
//
//        if (root.getRight() != null) {
//            printRange(root.getRight(), L, R, writer);
//        }
    }

    /**
     * Comment it before submitting
     */
    private static class Node {

        private int value;
        private Node left;
        private Node right;

        Node(Node left, Node right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
    //**/

    public static void main(String[] args) throws Exception {
        test();
    }

    private static void test() throws IOException {
        Node node1 = new Node(null, null, 2);
        Node node2 = new Node(null, node1, 1);
        Node node3 = new Node(null, null, 8);
        Node node4 = new Node(null, node3, 8);
        Node node5 = new Node(node4, null, 9);
        Node node6 = new Node(node5, null, 10);
        Node node7 = new Node(node2, node6, 5);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        printRange(node7, 2, 8, writer);
        writer.flush();
        // expected output: 2 5 8 8
    }
}