package ya.test.sprint2final;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
https://contest.yandex.ru/contest/22781/run-report/68827124/

-- ПРИНЦИП РАБОТЫ --
Двухсторонняя очередь - очередь с возможностью извлекать и класть элементы
с двух сторон - с начала и с конца.
При реализации используется кольцевой буфер - это накладывает ограничения на максимальное
кол-во элементов, которое можно поместить в очередь. В то же самое время данная очередь
всегда занимает определенный размер в памяти и обеспечивет константное время
выполнения операций.
Из особенностей реализации, данная очередь не позволяет хранить null элементы из-за того,
что null используется как маркерный элемент, говорящий о том, что очерь пустая.
Наибольщая сложность реализации заключалась в необходимости обработать граничный случай,
когда очередь пустая. Т.е. когда указатель head и tail, указывают на один и тот же
элемент. Этот случай отличается от всех остальных, в том числе, когда очередь полностью
заполенна больше указатели head и tail никогда не пересекаются и
методы popBack и popFront, ни в каких других случаях не возвращают один и тот же элемент.
Была так же идея не использовать два указателя - head и tail, а использовать
один указатель, а второй вычислять как первый указатель + текущий размер очереди.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Из описания алгоритма следует, что элементы из двусторонней очереди мы можем извлекать
только с краев. Так как в реализации у нас есть только методы выполняющие непосредственно
оперция положить/получить и нет методов, которые предоставляли бы какой-либо доступ к
внутренним структурам, то можно считать, что не при каких действиях пользователя, алгоритм
работы с данной очередью не будет нарушен. чтд. ;-)

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
В качестве хранилища элементов используется массив. Доступ к элементам массива
осуществялется по индексу с константной временной сложностью.
В любой момент времени в нашей реализации очереди имеется укзатель на голову и хвост.
Соответственно получение элемента очереди с конца или с начала является получением
элемента из массива по известному индексу O(1).
Так же имеются незначительные константные расходы на поддержание актуального значения
указателей головы и хвоста, которые не растут в зависимости от кол-ва элементов
в очерди.
 Cуммарная временная сложность (всей программы) будет зависеть от кол-во выполняемых
комманд над этой очередью и равно O(n).

 -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Пространсвенная сложность определяется максимальным размером очереди, при этом не завист
от кол-ва элементов хранящихся на текущий момент времени в очерди и всегда не больше
чем max_size указанный на этапе конструирования очереди.
Так же есть некоторая константная пространсвенная сложность из-за необходимости хранить
указатели и текущий размер очереди. Поэтому пространсвенная сложность очереди
оказывается равной O(max_size)
*/
public class Deque {

    private final Integer[] queue;

    private int head = 0;

    private int tail = 0;

    private int size = 0;


    public Deque(int max_size) {
        queue = new Integer[max_size];
    }

    /**
     * Возвращает true если успешно добавили элемент в очередь, иначе false
     */
    public boolean pushBack(Integer value) {
        if (value == null) {
            return false;
        }

        if (size >= queue.length) {
            return false;
        }

        if (size != 0) {
            tail = (tail + 1) % queue.length;
        }
        queue[tail] = value;

        size++;

        return true;
    }

    public Integer popBack() {
        if (size == 0) {
            return null;
        }

        size--;

        Integer value = queue[tail];

        if (size == 0) {
            tail = 0;
            head = 0;
        } else {
            tail = (queue.length + (tail - 1)) % queue.length;
        }

        return value;
    }

    public boolean pushFront(Integer value) {
        if (value == null) {
            return false;
        }

        if (size >= queue.length) {
            return false;
        }

        if (size != 0) {
            head = (queue.length + (head - 1)) % queue.length;
        }
        queue[head] = value;
        size++;

        return true;
    }

    public Integer popFront() {
        if (size == 0) {
            return null;
        }

        size--;
        Integer value = queue[head];

        if (size == 0) {
            head = 0;
            tail = 0;
        } else {
            head = (head + 1) % queue.length;
        }

        return value;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int commands = Integer.parseInt(reader.readLine());
            int maxQueueSize = Integer.parseInt(reader.readLine());

            Deque deque = new Deque(maxQueueSize);

            for (int i = 0; i < commands; i++) {
                String[] s = reader.readLine().split(" ");

                String command = s[0];
                int value = 0;

                if (s.length > 1) {
                    value = Integer.parseInt(s[1]);
                }

                if ("pop_front".equals(command)) {
                    Integer pop = deque.popFront();
                    if (pop == null) {
                        writer.write("error\n");
                    } else {
                        writer.write(pop + "\n");
                    }
                } else if ("pop_back".equals(command)) {
                    Integer pop = deque.popBack();
                    if (pop == null) {
                        writer.write("error\n");
                    } else {
                        writer.write(pop + "\n");
                    }
                } else if ("push_back".equals(command)) {
                    boolean pushResult = deque.pushBack(value);
                    if (!pushResult) {
                        writer.write("error\n");
                    }
                } else if ("push_front".equals(command)) {
                    boolean pushResult = deque.pushFront(value);
                    if (!pushResult) {
                        writer.write("error\n");
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            }
        }
    }
}
