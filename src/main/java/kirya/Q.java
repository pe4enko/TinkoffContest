package kirya;

import java.util.Deque;
import java.util.LinkedList;

public class Q<T> {

    Deque<T> writeStack = new LinkedList<>();
    Deque<T> readStack = new LinkedList<>();

    public boolean add(T o) {
        writeStack.push(o);
        return true;
    }

    public T remove() {
        boolean empty = readStack.isEmpty();

        if (empty) {
            while (!writeStack.isEmpty()) {
                T elem = writeStack.pop();
                readStack.push(elem);
            }
        }

        return readStack.pop();
    }

    public static void main(String[] args) {
        Q<Integer> q = new Q<>();

        q.add(1);
        q.add(2);
        q.add(3);

        Integer remove = q.remove();
        System.out.println(remove);

        remove = q.remove();
        System.out.println(remove);

        q.add(4);
        q.add(5);

        remove = q.remove();
        System.out.println(remove);

        ///////
        remove = q.remove();
        System.out.println(remove);

        remove = q.remove();
        System.out.println(remove);


    }
}
