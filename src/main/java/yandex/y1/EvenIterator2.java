package yandex.y1;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class EvenIterator2 implements Iterator<Integer> {
    private final Iterator<Integer> iterator;

    private List<Integer> list;

    public EvenIterator2(Collection<Integer> items) {
        iterator = items.stream()
                .filter(integer -> integer % 2 == 0)
                .iterator();
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public Integer next() {
        return iterator.next();
    }
}