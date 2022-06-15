package proit;

public class Node {

    String name;
    Node next;

    public Node(String name, Node next) {
        this.name = name;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", next=" + next +
                '}';
    }

    public static void main(String[] args) {
        Node n3 = new Node("n3", null);
        Node n2 = new Node("n2", n3);
        Node n1 = new Node("n1", n2);

        Node invertedList = invert(n1);
        System.out.println(invertedList);
    }


    /**
     * Входящие параметры: Node1 -> Node2 -> Node3 Результат: Node3 -> Node2 -> Node1
     **/
    public static Node invert(Node list) {
        Node current = list;
        Node next = current.next;
        Node shouldBeNextNode = null;

        while (next != null) {
            next = current.next;
            current.next = shouldBeNextNode;

            shouldBeNextNode = current;
            current = next;
        }

        current.next = shouldBeNextNode;

        return current;
    }
}
