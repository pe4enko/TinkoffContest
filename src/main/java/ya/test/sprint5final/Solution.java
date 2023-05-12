package ya.test.sprint5final;

/**
 * https://contest.yandex.ru/contest/24810/run-report/69583506/
 * <p>
 * -- ПРИНЦИП РАБОТЫ --
 * Алгоритм состоит из нескольких этапов, первый этап - это поиск элемента удалить который необходимо.
 * Благодаря особой структуре дерева поиска работать с ним удобнее, чем с обычным деревом.
 * Каждый раз, когда мы выбираем, в каком поддереве продолжать поиск, у нас есть указатель-подсказка:
 * если элемент, записанный в текущей вершине, больше искомого, то нужно продолжить поиск в левом поддереве,
 * иначе — в правом. А если ключ вершины равен искомому, то поиск успешно завершается.
 * На втором этапе мы удаляем найденный элемент и восстанавливаем структуру дерева, так чтобы она соответствовала
 * бинарному дереву поиска. Для восстановлеиня структуры на место удаленного элемента необходимо поставить
 * какой-то другой узел. Значение этого узла должно быть: больше всех значений в левом поддереве удаляемой
 * вершины, меньше либо равно всем значениям в правом поддереве.
 * Таким узлом является узел с максимальным значением из левого поддерева. Или другими словами максимально правый
 * узел из левого поддерева. Для его поиска необходимо взять корневой элемент левого поддерева удаляемого узла
 * и спуститься вниз по дереву до тех пор, пока значения правого узла не станет равным null.
 * Таким образом мы найдем вершину, которая объединит распадающиеся деревья. При перемещении полученной вершины
 * на место удаляемого элемента возможны два случая, первый когда у найденной вершины нет дочерних элементов и
 * второй когда есть только левый дочерный узел. Для первого случая мы просто перемещаем найденный узел на место
 * удаляемого небоясь что дерево станет не корректным. Во втором же случае необходимо перед перемещением
 * перепривязать левое поддерево найденного "узла-замены" вместо правого узла родительского "узла-замены".
 * На этом удаление узла оканчивается.
 *
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Алгоритм корректен по построению, так как на каждом этапе удаления элемента мы поддерживаем дерево в корректном
 * состоянии. Если удаляемый узел не найден, то дерево не изменяется совсем и остается корректным.
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * 1. Поиск удаляемого элемента = O(h). В худшев случае высота дерева может быть равна кол-ву элементов в дереве.
 * 2. Поиск "элемента-земены" так же зависит от высоты дерева и от позиции удаляемого элемента. В худшем случае
 * если мы удаляем корень дерева поиск может занять O(h) времени. Если все элементы дерева только правые или только
 * левые то поиск займет O(n).
 * 3. Изменение указателей O(1) времени.
 * В сумме временная сложность будет равняться O(h) + O(h) = O(h). Или O(n) + O(n) = O(n) если дерево не сбалансирово
 * совсем.
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Для поиска удаляемого элемента используется рекурсивная реализация. Следовательно, для стека рекурсии необходима
 * дополнительная память, зависит от высоты дерева равна O(h). Таким образом пространственная сложность алгоритма будет
 * равняться кол-ву элементов в дереве + память для стека вызовов. O(n) + O(h) = O(n)
 */
public class Solution {

    /* Функция должна вернуть корень изменённого дерева. */
    public static Node remove(Node root, int key) {
        if (root == null) {
            return null;
        }

        if (root.getValue() == key) {
            root = delete(root);
        } else if (root.getLeft() != null && root.getLeft().getValue() == key) {
            //нашли элемент который надо удалить это root.left
            root.setLeft(delete(root.getLeft()));
        } else if (root.getRight() != null && root.getRight().getValue() == key) {
            root.setRight(delete(root.getRight()));
        } else {
            if (root.getValue() < key) {
                remove(root.getRight(), key);
            } else /* if (root.getValue() > key)*/ {
                remove(root.getLeft(), key);
            }
        }

        return root;
    }

    private static Node delete(Node keyEqualNode) {
        //Если удаляемый элемент имеет только ПРАВОЕ поддерево, то после удаления можно просто сослаться
        //на вершину этого поддерева из родительской ноды ранее указывающей на удаляемый элемент.
        if (keyEqualNode.getLeft() == null) {
            return keyEqualNode.getRight();
        }

        //Если удаляемый элемент имеет только ЛЕВОЕ поддерево, то после удаления можно просто сослаться
        //на вершину этого поддерева из родительской ноды ранее указывающей на удаляемый элемент.
        if (keyEqualNode.getRight() == null) {
            return keyEqualNode.getLeft();
        }

        //Находим элемент на который будем заменять удаляемый элемент.
        // Это будет самый правый (самый большой) элемент в левом поддереве.
        Node changeToNode = keyEqualNode.getLeft();
        /* Это родитель самого правого элемента в левом поддереве */
        Node parentNode = keyEqualNode;

        while (changeToNode.getRight() != null) {
            parentNode = changeToNode;
            changeToNode = changeToNode.getRight();
        }

        /* Корень правого поддерева из удаляемого узла*/
        Node right = keyEqualNode.getRight();
        /* Корень левого поддерева из удаляемого узла*/
        Node left = keyEqualNode.getLeft();

        //Нашли на какой элемент менять. Далее два варианта.
        //1) Вершина на которую будем заменять удаляемый элемент является листом
        //2) Вершина на которую будем заменять удаляемый элемент имеет детей, только левых
        // т.к. мы нашли самый правый узел уже.

        if (parentNode != keyEqualNode) {
            if (changeToNode.getLeft() != null) {
                parentNode.setRight(changeToNode.getLeft());
            } else {
                parentNode.setRight(null);
            }
            changeToNode.setLeft(left);
        }

        changeToNode.setRight(right);

        return changeToNode;
    }

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        {
            Node node1 = new Node(null, null, 2);
            Node node2 = new Node(node1, null, 3);
            Node node3 = new Node(null, node2, 1);
            Node node4 = new Node(null, null, 6);
            Node node5 = new Node(node4, null, 8);
            Node node6 = new Node(node5, null, 10);
            Node node7 = new Node(node3, node6, 5);
            Node newHead = remove(node7, 10);
            assert newHead.getValue() == 5;
            assert newHead.getRight() == node5;
            assert newHead.getRight().getValue() == 8;
        }

        {
            //  Удаление root
            Node node1 = new Node(null, null, 2);
            Node node2 = new Node(node1, null, 3);
            Node node3 = new Node(null, node2, 1);
            Node node4 = new Node(null, null, 6);
            Node node5 = new Node(node4, null, 8);
            Node node6 = new Node(node5, null, 10);
            Node node7 = new Node(node3, node6, 5);
            Node newHead = remove(node7, 5);
            assert newHead.getValue() == 3;
            assert newHead.getRight() == node6;
            assert newHead.getRight().getValue() == 10;
            assert newHead.getLeft() == node3;
            assert newHead.getLeft().getRight() == node1;
            assert newHead.getLeft().getLeft() == null;
        }

        {
            //  Удаление 10 с заполненной правой частью
            Node node8 = new Node(null, null, 14);
            Node node9 = new Node(null, node8, 12);

            Node node1 = new Node(null, null, 2);
            Node node2 = new Node(node1, null, 3);
            Node node3 = new Node(null, node2, 1);
            Node node4 = new Node(null, null, 6);
            Node node5 = new Node(node4, null, 8);
            Node node6 = new Node(node5, node9, 10);
            Node node7 = new Node(node3, node6, 5);

            Node newHead = remove(node7, 10);
            assert newHead.getValue() == 5;
            assert newHead.getRight() == node5;
            assert newHead.getRight().getValue() == 8;
            assert newHead.getLeft() == node3;
            assert newHead.getLeft().getRight() == node2;
            assert newHead.getLeft().getLeft() == null;
        }

        {
            Node node8 = new Node(null, null, 14);
            Node node9 = new Node(null, node8, 12);

            Node node1 = new Node(null, null, 2);
            Node node2 = new Node(node1, null, 3);
            Node node3 = new Node(null, node2, 1);
            Node node4 = new Node(null, null, 6);
            Node node5 = new Node(node4, null, 8);
            Node node6 = new Node(node5, node9, 10);
            Node node7 = new Node(node3, node6, 5);

            Node newHead = remove(node7, 8);
            assert newHead.getValue() == 5;
            assert newHead.getRight() == node6;
            assert newHead.getRight().getValue() == 10;
            assert newHead.getRight().getLeft() == node4;
            assert newHead.getRight().getLeft().getValue() == 6;
            assert newHead.getRight().getRight() == node9;
            assert newHead.getRight().getRight().getValue() == 12;
        }

        {
            Node node10 = new Node(null, null, 99);
            Node node9 = new Node(null, null, 72);
            Node node8 = new Node(node9, node10, 91);
            Node node7 = new Node(null, null, 50);
            Node node6 = new Node(null, null, 32);
            Node node5 = new Node(null, node6, 29);
            Node node4 = new Node(null, null, 11);
            Node node3 = new Node(node7, node8, 65);
            Node node2 = new Node(node4, node5, 20);
            Node node1 = new Node(node2, node3, 41);

            Node newHead = remove(node1, 41);
            assert newHead.getValue() == 32;
            assert newHead.getRight() == node3;
            assert newHead.getRight().getValue() == 65;

            assert newHead.getLeft() == node2;
            assert newHead.getLeft().getValue() == 20;
        }
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


        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
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
    }
}