package ya.test.sprint5final;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * https://contest.yandex.ru/contest/24810/run-report/69555726/
 * <p>
 * -- ПРИНЦИП РАБОТЫ --
 * Пирамидальная сортировка работает следующим образом: 1. Создается пустая бинарная,
 * невозростающая куча. 2. Вставляем в неё по одному все элементы массива, сохраняя свойства кучи. На вершине пирамиды
 * должен оказаться самый большой элемент (с большим кол-вом решенных задач) 3. Будем извлекать из неё наиболее
 * приоритетные элементы, удаляя их из кучи, при этом так же сохраняя свойства кучи так же как и при вставке.
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Так как на всех этапах работы с кучей мы поддерживаем ее корректность, то после каждой операции на вершине будет
 * располагаться участник с наибольщим кол-вом решенных задач. Таким образом при извлечении с вершины участника мы
 * всегда получаем наиболее успешного участника.
 * Алгоритм корректен по построению.
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Первый этап - создание пустой бинарной кучи занимает O(1) времени
 * Второй этап - вставка n элементов в кучу, занимает O(n * logN) времени.
 * Третий этап - удаление n элементов из кучи. При этом при удалении происходит просеивание вниз последнего элемента
 * из кучи. На каждом этапе просеивания выполняется сравнение не более чем двух сравнений элементов, а этапов столько
 * же сколько высота дерева. Таким образом удаление происходит за O(n * logN).
 * Т.е. временная сложность пирамидальной сортировки n элементов будет
 * равно O(n * logN) + O(n * logN) + O(1) = O(n * logN).
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Для хранения элементов в куче используется массив. Размер массива равен размеру кучи + 1 элемент.
 * Более дополнительной памяти не выделяется. Поэтому пространсвенная сложность будет O(n).
 */
public class HeapSort {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = parseInt(reader.readLine());

            Heap<Intern> heap = new Heap<>(n);

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(reader.readLine());

                String login = st.nextToken();
                int solvedTasks = parseInt(st.nextToken());
                int penalty = parseInt(st.nextToken());

                heap.push(new Intern(login, solvedTasks, penalty));
            }

            while (!heap.isEmpty()) {
                Intern intern = heap.pop();
                writer.write(intern.login + "\n");
            }

            writer.flush();
        }
    }

    private static class Heap<T extends Comparable<? super T>> {

        private final Object[] arr;
        private int size;

        public Heap(int size) {
            this.arr = new Object[size + 1];
        }

        public void push(T elem) {
            if (size + 1 == arr.length) {
                throw new IndexOutOfBoundsException();
            }
            size++;

            arr[size] = elem;

            siftUp(arr, size);
        }

        public T pop() {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }

            Object result = arr[1];
            arr[1] = arr[size];
            arr[size] = null;

            size--;

            siftDown(arr, 1);

            //noinspection unchecked
            return (T) result;
        }

        public boolean isEmpty() {
            return size <= 0;
        }

        private int siftUp(Object[] heap, int idx) {
            if (idx <= 1) {
                return idx;
            }
            int parentIndex = idx / 2;

            if (compare(heap[parentIndex], heap[idx]) < 0) {
                swap(heap, idx, parentIndex);
                return siftUp(heap, parentIndex);
            }

            return idx;
        }

        private int siftDown(Object[] heap, int idx) {
            int leftIdx = 2 * idx;
            int rightIdx = leftIdx + 1;

            if (leftIdx > size) {
                return idx;
            }

            int indexLargest;
            if (rightIdx <= size && compare(heap[leftIdx], heap[rightIdx]) < 0) {
                indexLargest = rightIdx;
            } else {
                indexLargest = leftIdx;
            }

            if (compare(heap[idx], heap[indexLargest]) < 0) {
                swap(heap, indexLargest, idx);
                return siftDown(heap, indexLargest);
            }

            return idx;
        }

        private static void swap(Object[] heap, int idx, int parentIndex) {
            Object tmp = heap[parentIndex];
            heap[parentIndex] = heap[idx];
            heap[idx] = tmp;
        }

        final int compare(Object o1, Object o2) {
            //noinspection unchecked
            return ((Comparable<? super T>) o1).compareTo((T) o2);
        }
    }


    public static class Intern implements Comparable<Intern> {

        public final String login;
        public final int solvedTasks;
        public final int penalty;

        public Intern(String login, int solvedTasks, int penalty) {
            this.login = login;
            this.solvedTasks = solvedTasks;
            this.penalty = penalty;
        }

        /**
         * Тимофей решил сортировать таблицу результатов следующим образом: при сравнении двух участников выше будет
         * идти тот, у которого решено больше задач. При равенстве числа решённых задач первым идёт участник с меньшим
         * штрафом. Если же и штрафы совпадают, то первым будет тот, у которого логин идёт раньше в алфавитном
         * (лексикографическом) порядке.
         */
        @Override
        public int compareTo(Intern other) {
            //при сравнении двух участников выше будет идти тот, у которого решено больше задач
            int compareResult = Integer.compare(this.solvedTasks, other.solvedTasks);
            if (compareResult != 0) {
                return compareResult;
            }

            //При равенстве числа решённых задач первым идёт участник с меньшим штрафом
            int compareResultPenalty = -Integer.compare(this.penalty, other.penalty);
            if (compareResultPenalty != 0) {
                return compareResultPenalty;
            }

            // Если же и штрафы совпадают, то первым будет тот, у которого логин идёт раньше
            // в алфавитном (лексикографическом) порядке
            return -this.login.compareTo(other.login);
        }

        @Override
        public String toString() {
            return "Intern{" +
                    "login='" + login + '\'' +
                    ", solvedTasks=" + solvedTasks +
                    ", penalty=" + penalty +
                    '}';
        }
    }
}
