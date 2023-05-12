package kirya;

public class TreeDeep {

    private static int maxDeep = 0;
    private static Node node;

    public static void main(String[] args) {

        Node n4 = new Node(4, null, null);
        Node n3 = new Node(3, null, null);
        Node n2 = new Node(2, n3, n4);
        Node n1 = new Node(1, null, null);
        Node n0 = new Node(0, n2, n1);

        deep(n0, 0);

        System.out.println(maxDeep + " " + node.val);

    }

    private static void deep(Node n, int deep) {
        if (n == null) {
            return;
        }

        if (maxDeep < deep) {
            maxDeep = deep;
            node = n;
        }

        deep(n.left, ++deep);
        deep(n.right, ++deep);
    }


    public static class Node {

        int val;
        Node left;
        Node right;

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
