package ya.test.sprint2.f;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class StackMax {

    public StackMax(int size) {
        stack = new Integer[size];
    }

    private final Integer[] stack;
    private int topElementIndex = -1;

    public void push(int value) {
        topElementIndex++;
        stack[topElementIndex] = value;
    }

    public Integer pop() {
        if (topElementIndex < 0) {
            return null;
        }
        int value = stack[topElementIndex];
        stack[topElementIndex] = null;
        topElementIndex--;
        return value;
    }

    public Integer getMax() {
        if (topElementIndex < 0) {
            return null;
        }
        Integer maxValue = null;
        for (int i = 0; i <= topElementIndex; i++) {
            Integer integer = stack[i];
            if (maxValue == null || maxValue < integer) {
                maxValue = integer;
            }
        }
        return maxValue;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int size = readInt(reader);
            StackMax stack = new StackMax(size);
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
