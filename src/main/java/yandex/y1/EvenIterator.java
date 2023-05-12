package yandex.y1;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 Напишите реализацию Java-итератора по заданному на входе набору целых чисел, возвращающий только четные (по значению) числа из этого набора.
 TODO требования:
 - реализовать Iterator<Integer>
 - придумать интерфейс для конечного пользователя (что принимать на вход)
 - БЕЗ удаления элементов
 * Реализовать итератор, пробегающийся только по четным числам из набора
 */
public class EvenIterator implements Iterator<Integer> {

    private final Collection<Integer> items;
    private final Iterator<Integer> iterator;

    public EvenIterator(Collection<Integer> items) {
        this.items = items;
        iterator = items.iterator();
    }

    private Integer nextElem = null;

    public boolean hasNext() {
        if (nextElem != null) {
            return true;
        }

        while (iterator.hasNext()) {
            Integer next = iterator.next();
            int div = next % 2;
            if (div == 0) {
                nextElem = next;
                return true;
            }
        }

        return false;
    }

    public Integer next() {
        if (hasNext()) {
            Integer tmp = nextElem;
            nextElem = null;

            return tmp;
        }

        throw new NoSuchElementException();
    }
}