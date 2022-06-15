package ya.test.sprint2.i;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Астрологи объявили день очередей ограниченного размера. Тимофею нужно написать класс MyQueueSized, который принимает
 * параметр max_size, означающий максимально допустимое количество элементов в очереди.
 * <p>
 * Помогите ему —– реализуйте программу, которая будет эмулировать работу такой очереди. Функции, которые надо
 * поддержать, описаны в формате ввода.
 * <p>
 * Формат ввода В первой строке записано одно число — количество команд, оно не превосходит 5000. Во второй строке задан
 * максимально допустимый размер очереди, он не превосходит 5000. Далее идут команды по одной на строке. Команды могут
 * быть следующих видов:
 * <p>
 * push(x) — добавить число x в очередь; pop() — удалить число из очереди и вывести на печать; peek() — напечатать
 * первое число в очереди; size() — вернуть размер очереди; При превышении допустимого размера очереди нужно вывести
 * «error». При вызове операций pop() или peek() для пустой очереди нужно вывести «None». Формат вывода Напечатайте
 * результаты выполнения нужных команд, по одному на строке.
 */
public class MyQueueSized {

    private final Integer[] queue;

    private int head = 0;

    private int tail = 0;
    private int size = 0;


    public MyQueueSized(int max_size) {
        queue = new Integer[max_size];
    }

    /**
     * Возвращает true если успешно добавили элемент в очередь, иначе false
     */
    public boolean push(int value) {
        if (size >= queue.length) {
            return false;
        }

        size++;
        queue[tail] = value;
        tail = (tail + 1) % queue.length;

        return true;
    }

    public Integer pop() {
        if (size == 0) {
            return null;
        }
        size--;
        Integer value = queue[head];
        head = (head + 1) % queue.length;

        return value;
    }

    public Integer peek() {
        if (size == 0) {
            return null;
        }

        return queue[head];
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int commands = readInt(reader);
            int maxQueueSize = readInt(reader);

            MyQueueSized stack = new MyQueueSized(maxQueueSize);
            for (int i = 0; i < commands; i++) {
                String[] s = reader.readLine().split(" ");

                String command = s[0];
                int value = 0;

                if (s.length > 1) {
                    value = Integer.parseInt(s[1]);
                }

                if ("peek".equals(command)) {
                    Integer peek = stack.peek();
                    if (peek == null) {
                        writer.write("None\n");
                    } else {
                        writer.write(peek + "\n");
                    }
                } else if ("pop".equals(command)) {
                    Integer pop = stack.pop();
                    if (pop == null) {
                        writer.write("None\n");
                    } else {
                        writer.write(pop + "\n");
                    }
                } else if ("push".equals(command)) {
                    boolean pushResult = stack.push(value);
                    if (!pushResult) {
                        writer.write("error\n");
                    }
                } else if ("size".equals(command)) {
                    writer.write(stack.getSize() + "\n");
                } else {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
