package ya.test.sprint2.e;
/*
Comment it before submitting*/
class Node<V> {
    public V value;
    public Node<V> next;
    public Node<V> prev;

    public Node(V value, Node<V> next, Node<V> prev) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", next=" + next +
                '}';
    }
}

public class Solution {
    public static Node<String> solution(Node<String> head) {
        if (head.next == null) {
            return head;
        }

        Node<String> current = head;
        Node<String> currentNext;
        Node<String> currentPrev;

        while (current.next != null)  {
            currentNext = current.next;
            currentPrev = current.prev;

            current.next = currentPrev;
            current.prev = currentNext;

            current = currentNext;
        }

        currentNext = current.next;
        currentPrev = current.prev;

        current.next = currentPrev;
        current.prev = currentNext;


        return current;
    }

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        Node<String> node3 = new Node<>("node3", null, null);
        Node<String> node2 = new Node<>("node2", node3, null);
        Node<String> node1 = new Node<>("node1", node2, null);
        Node<String> node0 = new Node<>("node0", node1, null);
        node1.prev = node0;
        node2.prev = node1;
        node3.prev = node2;
        Node<String> newNode = solution(node0);
        /* result is :*/
        assert newNode == node3;
        assert node3.next == node2;
        assert node2.next == node1;
        assert node2.prev == node3;
        assert node1.next == node0;
        assert node1.prev == node2;
        assert node0.prev == node1;
    }
}