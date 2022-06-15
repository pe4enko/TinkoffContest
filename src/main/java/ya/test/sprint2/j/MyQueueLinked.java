package ya.test.sprint2.j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

/**
 * J. Списочная очередь Любимый вариант очереди Тимофея — очередь, написанная с использованием связного списка. Помогите
 * ему с реализацией. Очередь должна поддерживать выполнение трёх команд:
 * <p>
 * get() — вывести элемент, находящийся в голове очереди, и удалить его. Если очередь пуста, то вывести «error». put(x)
 * — добавить число x в очередь size() — вывести текущий размер очереди Формат ввода В первой строке записано количество
 * команд n — целое число, не превосходящее 1000. В каждой из следующих n строк записаны команды по одной строке.
 * <p>
 * Формат вывода Выведите ответ на каждый запрос по одному в строке.
 */
public class MyQueueLinked {

    private final LinkedList<Integer> queue;

    public MyQueueLinked() {
        queue = new LinkedList<>();
    }

    public void put(int value) {
        queue.addLast(value);
    }

    public Integer get() {
        try {
            return queue.removeFirst();
        } catch (Exception e) {
            return null;
        }
    }

    public int getSize() {
        return queue.size();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int commands = readInt(reader);

            MyQueueLinked stack = new MyQueueLinked();
            for (int i = 0; i < commands; i++) {
                String[] s = reader.readLine().split(" ");

                String command = s[0];
                int value = 0;

                if (s.length > 1) {
                    value = Integer.parseInt(s[1]);
                }

                if ("get".equals(command)) {
                    Integer pop = stack.get();
                    if (pop == null) {
                        writer.write("error\n");
                    } else {
                        writer.write(pop + "\n");
                    }
                } else if ("put".equals(command)) {
                    stack.put(value);
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
