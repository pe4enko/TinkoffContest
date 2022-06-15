package ya.test.sprint2.d;

import java.util.Objects;

/*
Comment if before submitting*/
class Node<V> {

    public V value;
    public Node<V> next;

    public Node(V value, Node<V> next) {
        this.value = value;
        this.next = next;
    }
}


public class Solution {

    public static int solution(Node<String> head, String elem) {
        if (Objects.equals(head.value, elem)) {
            return 0;
        }

        int index = 0;

         Node<String> current = head;

        while (current.next != null) {
            index++;
            if (Objects.equals(current.next.value, elem)) {
                return index;
            }

            current = current.next;
        }

        return -1;
    }

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        Node<String> node3 = new Node<>("node3", null);
        Node<String> node2 = new Node<>("node2", node3);
        Node<String> node1 = new Node<>("node1", node2);
        Node<String> node0 = new Node<>("node0", node1);

        int hasIndex = solution(node0, "node3");

        System.out.println(hasIndex);
    }
}