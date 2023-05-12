package kirya;

import static java.lang.System.out;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrIterator implements Iterator<Integer> {

    final private int[] digits;

    private int pos = 0;

    private Integer next = null;

    public ArrIterator(int[] digits) {
        this.digits = digits;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        ArrIterator it = new ArrIterator(arr);

        while (it.hasNext()) {
            Integer next = it.next();
            out.println(next);
        }
    }

    @Override
    public boolean hasNext() {
        if (next != null) {
            return true;
        } else {
            next = findNext();
        }

        return next != null;
    }

    @Override
    public Integer next() {
        if (next != null) {
            Integer tmp = next;
            next = null;
            return tmp;
        } else {
            next = findNext();
        }

        if (next != null) {
            Integer tmp = next;
            next = null;
            return tmp;
        }

        throw new NoSuchElementException();
    }

    private Integer findNext() {
        while (pos < digits.length) {
            int digit = digits[pos];
            pos++;
            if (digit % 2 == 0) {
                return digit;
            }
        }
        return null;
    }
}
