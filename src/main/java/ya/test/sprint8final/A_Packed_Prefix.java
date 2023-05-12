package ya.test.sprint8final;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://contest.yandex.ru/contest/26133/run-report/70283550/
 */
public class A_Packed_Prefix {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

//            System.out.println(unpack("2[a]2[ab]"));
//            System.out.println(unpack("3[a]2[r2[t]]"));
//            System.out.println(unpack("a2[aa3[b]]"));

            int n = Integer.parseInt(reader.readLine());

            List<String> strings = new ArrayList<>(n);

            for (int i = 0; i < n; i++) {
                strings.add(unpack(reader.readLine()));
            }

            if (strings.size() == 1) {
                writer.write(strings.get(0));
                writer.flush();
                return;
            }

            String template = strings.get(0);

            for (int i = 0; i < template.length(); i++) {
                char symbol = template.charAt(i);

                for (int j = 1; j < n; j++) {
                    String s = strings.get(j);
                    if (s.length() < i) {
                        writer.write(template.substring(0, i));
                        return;
                    }

                    char c = s.charAt(i);
                    if (symbol != c) {
                        writer.write(template.substring(0, i));
                        return;
                    }
                }
            }

            writer.write(template);
            writer.flush();
        }

    }

    public static String unpack(String packedString) {
        return unpack(packedString, 0).str;
    }

    private static StrAndPos unpack(String packedString, int from) {
        StringBuilder sb = new StringBuilder();

        Integer startDigitPosition = null;

        for (int i = from; i < packedString.length(); i++) {
            char symbol = packedString.charAt(i);
            if (']' == symbol) {
                return new StrAndPos(sb.toString(), i);
            } else if ('[' == symbol) {
                int digit = Integer.parseInt(packedString.substring(startDigitPosition, i));
                StrAndPos unpack = unpack(packedString, i + 1);
                sb.append(unpack.str.repeat(digit));
                i = unpack.pos;
                startDigitPosition = null;
            } else if (Character.isDigit(symbol)) {
                if (startDigitPosition == null) {
                    //todo: по условию тут только однозначные цифры, иначе тут не правильно
                    startDigitPosition = i;
                }
            } else {
                sb.append(symbol);
            }
        }

        return new StrAndPos(sb.toString(), from);
    }

    private static class StrAndPos {
        String str;
        int pos;

        public StrAndPos(String str, int pos) {
            this.str = str;
            this.pos = pos;
        }
    }
}
