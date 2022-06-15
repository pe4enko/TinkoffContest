package ya.test.sprint2.g;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Objects;

public class MaxEffective {

    private final Integer[] stack;

    private int topElementIndex = -1;

    LinkedList<Integer> maxStack = new LinkedList<>();

    Integer maxValue = null;

    public MaxEffective(int size) {
        stack = new Integer[size];
    }

    public void push(int value) {
        topElementIndex++;
        stack[topElementIndex] = value;

        if (maxValue == null || maxValue <= value) {
            maxValue = value;
            maxStack.push(value);
        }
    }

    public Integer pop() {
        if (topElementIndex < 0) {
            return null;
        }
        int value = stack[topElementIndex];
        stack[topElementIndex] = null;
        topElementIndex--;

        if (Objects.equals(maxValue, value)) {
            maxStack.pop();
            maxValue = maxStack.peek();
        }

        return value;
    }

    public Integer getMax() {
        return maxValue;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int size = readInt(reader);
            MaxEffective stack = new MaxEffective(size);
            for (int i = 0; i < size; i++) {
                String[] s = reader.readLine().split(" ");

                String command = s[0];
                int value = 0;

                if (s.length > 1) {
                    value = Integer.parseInt(s[1]);
                }

                if ("get_max".equals(command)) {
                    Integer max = stack.getMax();
                    writer.write(max == null ? "None\n" : max + "\n");
                } else if ("push".equals(command)) {
                    stack.push(value);
                } else if ("pop".equals(command)) {
                    Integer pop = stack.pop();
                    if (pop == null) {
                        writer.write("error\n");
                    }
                }
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
