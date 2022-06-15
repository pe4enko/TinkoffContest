package ya.test.sprint2.c;

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

    public static Node<String> solution(Node<String> head, int idx) {
        if (idx == 0) {
            return head.next;
        }

        int i;
        Node<String> current = head;
        Node<String> previousNode = current; //это 0 элемент
        Node<String> toDelNode;

        while (idx > 1) {
            previousNode = current.next;
            current = previousNode;
            idx--;
        }

        toDelNode = previousNode.next;
        Node<String> nextNode = toDelNode.next;
        previousNode.next = nextNode;
        return  head;
    }

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        Node<String> node3 = new Node<>("node3", null);
        Node<String> node2 = new Node<>("node2", node3);
        Node<String> node1 = new Node<>("node1", node2);
        Node<String> node0 = new Node<>("node0", node1);
        Node<String> newHead = solution(node0, 3);
        // result is : node0 -> node2 -> node3
        System.out.println(newHead);
    }
}