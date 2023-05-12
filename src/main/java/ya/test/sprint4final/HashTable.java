package ya.test.sprint4final;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * https://contest.yandex.ru/contest/24414/run-report/69328135/
 * -- ПРИНЦИП РАБОТЫ --
 * Хэш таблица используется для хранения данных.
 * Данная хэш таблица предоставляет методы для получения данных их удаления и сохранения по указанному ключу.
 * Для хранения используются так называемые "корзины" в которых хранятся данные обернутые в специальную
 * обертку Node. Корзина представляет собой обычную ячейку массива. Для получения индекса ячейки массива используется
 * хэш функция. В случае если случаются коллизиции и для разных объектов вычисляются одинаковые хэши
 * тогда данные складываются друг за другому при этом каждый Node имеет ссылку на следующий Node с таким же
 * хэшем.
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Для того, чтобы положить данные в хэш таблицу необходимо посчиать хэш ключа и разделить полученный хэш
 * на кол-во корзин таким образом мы найдем ячейку в которую необходимо класть данные с переданным ключем.
 * Если нам требуется получить данные с таким же ключем, то мы по сути должны сделать точно такие же операции.
 * При этом мы можем однозначно сказать, выполняя одни и те же действия мы всегда прийдем к одному и то
 * му же результату. В случае если у нас происходит коллизиция, то необходимо заменить имеющийся Node на Node,
 * который будет ссылаться на уже имеюищйся Node. Затем если мы захотим получить ранее положенные по ключу данные,
 * то нам придется пройтись по всем ссылкам Node указывающий на другой Node, и выполнить сравнение ключей,
 * если ключи равны то мы нашли наше искомое значение. Никаких других данных, которые мы не клали в хэш
 * появиться не могут.
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Сложность добавления в худшем случае - когда все элементы имеют одинаковый хэш и кладутся в одну корзину.
 * Так как мы храним только с уникальным ключем то даже при добавлении нам необходимо просматирвать все элементы
 * корзины и проверять на равенство текущему элементу который мы добавляем. Итого получится сложность добавления
 * одного элемента будет зависеть от кол-ва уже имеющихся элементов, т.е. O(n)
 * Сложность поиска в худшем случае так же в случае когда все элементы имеют одинаковый хэш и попадают в одну и ту
 * же корзину. При этом получается нам необходимо найти нужную корзину O(1) и
 * перебрать все элементы в этой корзине, т.е. O(n)
 * Аналогично при удалении. O(n)
 * <p>
 * В случае если хэш имеют хорошее распределение, а этом можно сказать про ключи с типом Integer,
 * то в одну и ту же корзину будут попадать только одинаковые ключи либо ключи отличающиеся между собой
 * на число корзин. И в таком случае нам не придется перебирать в цепочке много ключей и т.е.
 * все операции будут иметь константную временную сложность равную вычислению индекса корзины и доступа
 * в массиве по этому индексу.
 *
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Изначально мы выделяем массив для хранения 100_000 Node.
 * Данный массив никак не изменяется и не рехэшируется при изменении требований к количеству
 * хранимых данных. При этом Node могут выстраиваться в цепочку и выделять еще дополнительно память для
 * хранения ключа и значния. Поэтому пространсвенная сложность будет O(n).
 *
 */
public class HashTable {

    private final Node[] bucket;

    public HashTable(int max_size) {
        bucket = new Node[max_size];
    }

    public HashTable() {
        this(100_000);
    }

    public void put(Integer key, Integer value) {
        int m = getBucketIndex(key);

        Node node = bucket[m];

        if (node == null) {
            bucket[m] = new Node(key, value, null);
        } else {
            Node firstNode = node;
            do {
                if (node.key.equals(key)) {
                    node.value = value;
                    return;
                }
                node = node.next;
            } while (node != null);

            bucket[m] = new Node(key, value, firstNode);
        }
    }

    public Integer get(Integer key) {
        int m = getBucketIndex(key);

        Node node = bucket[m];

        if (node == null) {
            return null;
        }

        do {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        } while (node != null);

        return null;
    }

    public Integer delete(Integer key) {
        int m = getBucketIndex(key);

        Node node = bucket[m];

        if (node == null) {
            return null;
        }

        Node next = node.next;
        if (next == null) {
            //одиночное значение
            if (node.key.equals(key)) {
                bucket[m] = null;
                return node.value;
            }
        } else {
            //связанный список
            if (node.key.equals(key)) {
                bucket[m] = next;
                return node.value;
            } else {
                Node prevNode = node;
                node = node.next;

                do {
                    if (node.key.equals(key)) {
                        prevNode.next = node.next;
                        return node.value;
                    }
                    prevNode = node;
                    node = node.next;
                } while (node != null);
            }
        }

        return null;
    }

    private int getBucketIndex(Integer key) {
        return key % bucket.length;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int commands = Integer.parseInt(reader.readLine());

            HashTable hashTable = new HashTable();

            for (int i = 0; i < commands; i++) {
                String[] s = reader.readLine().split(" ");

                String command = s[0];
                int value = 0;
                int key = 0;

                if (s.length == 2) {
                    key = Integer.parseInt(s[1]);
                } else if (s.length == 3) {
                    key = Integer.parseInt(s[1]);
                    value = Integer.parseInt(s[2]);
                }

                if ("put".equals(command)) {
                    hashTable.put(key, value);
                } else if ("get".equals(command)) {
                    Integer val = hashTable.get(key);
                    if (val == null) {
                        writer.write("None\n");
                    } else {
                        writer.write(val + "\n");
                    }
                } else if ("delete".equals(command)) {
                    Integer val = hashTable.delete(key);
                    if (val == null) {
                        writer.write("None\n");
                    } else {
                        writer.write(val + "\n");
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    /**
     * Так как это внутренний класс, то все поля, даже если они приватные будут доступны родительскому классу.
     * И делать для них геттеры и сеттеры нет смысла.
     * Не нашел ваших контактов в слаке.
     */
    private static class Node {
        private final Integer key;

        private Integer value;

        private Node next;

        public Node(Integer key, Integer value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
