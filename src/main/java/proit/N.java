package proit;

public class N {

    String name;
    N next;

    public N(String name, N next) {
        this.name = name;
        this.next = next;
    }

    public static void main(String[] args) {
        N n3 = new N("n3", null);
        N n2 = new N("n2", n3);
        N n1 = new N("n1", n2);

        N invertedList = invert(n1);
        invertedList = invert(invertedList);
        System.out.println(invertedList);
    }

    /**
     * Входящие параметры: Node1 -> Node2 -> Node3 Результат: Node3 -> Node2 -> Node1
     **/
    public static N invert(N list) {
        N currentNode = list;

        N replaceTo = null;
        N prevNode = list;

        while (currentNode != null) {
            prevNode = currentNode;

            N savedNextNode = currentNode.next;
            currentNode.next = replaceTo;
            replaceTo = currentNode;
            currentNode = savedNextNode;
        }

        return prevNode;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", next=" + next +
                '}';
    }
}
