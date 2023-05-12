package ya.test.sprint8final;

import static java.lang.System.in;
import static java.lang.System.out;

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
 * https://contest.yandex.ru/contest/26133/run-report/70512067/
 * <pre>
 * -- ПРИНЦИП РАБОТЫ --
 * Считываем строки из ввода. Каждую строку распаковываем согласно правилам распаковки. Далее берем первую распакованную
 * строку, перебираем каждый ее символ с каждым символом из оставшихся строк. Продолджаем сравнение до тех пор пока не
 * встретятся различающиеся символы. Символы встретившиеся до первого различающегося символа являются оюбщим префиксом.
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Алгоритм корректен по построению.
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * 1. Необходимо распаковать n строк каждая из которых длиной Li. Распаковка одной строки занимает O(Li).
 * Распаковка всех строк O(L), где L сумма всех символов в распаковываемых строках.
 * 2. Перебор всех символов для проверки на равенство будет выполняться на уже распакованных строках. Длина
 * распакованных строк будет сильно зависеть от ЗС, если я правильно понимаю то если ЗС увеличивается на 3 символа то
 * распакованная строка в этом случае в худшем случае увеливается в 9 раз.
 *  ЗС                  |ЗС| -> |Распакованная строка|
 * 9[a]              =   4   ->  9
 * 9[9[a]]           =   7   ->  81
 * 9[9[9[a]]]        =   10  ->  729
 * 9[9[9[9[a]]]]     =   13  ->  6561
 * 9[9[9[9[9[a]]]]]  =   16  ->  59049
 * Сложно назвать такую зависимость роста как O(n), видно что начиная с 7 длина распакованной строки начинает расти
 * даже быстрее чем n^2
 * Итого: O(Li)
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Хранение всех строк O(L), где L сумма всех символов в распаковываемых строках.
 * </pre>
 */
public class A_Packed_Prefix_Iterative {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))) {

            int n = Integer.parseInt(reader.readLine());

            List<String> unpackedStrings = new ArrayList<>(n);

            int minTemplateLength = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                String unpackedString = unpack(reader.readLine());
                minTemplateLength = Math.min(minTemplateLength, unpackedString.length());

                unpackedStrings.add(unpackedString);
            }

            if (unpackedStrings.size() == 1) {
                writer.write(unpackedStrings.get(0));
                writer.flush();
                return;
            }

            String template = unpackedStrings.get(0);

            for (int i = 0; i < minTemplateLength; i++) {
                char symbol = template.charAt(i);

                for (int j = 1; j < n; j++) {
                    String s = unpackedStrings.get(j);
                    if (s.length() < i) {
                        writeStrToPosition(template, i, writer);
                        return;
                    }

                    char c = s.charAt(i);
                    if (symbol != c) {
                        writeStrToPosition(template, i, writer);
                        return;
                    }
                }
            }

            writer.write(template);
            writer.flush();
        }
    }

    private static void writeStrToPosition(String whatToWrite, int toPosition, BufferedWriter writer)
            throws IOException {

        for (int i = 0; i < toPosition; i++) {
            writer.write(whatToWrite.charAt(i));
        }
    }

    private static String unpack(String packedString) {

        Deque<SbAndDigit> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        stack.push(new SbAndDigit(sb, 0));

        Integer digit = null;

        for (int i = 0; i < packedString.length(); i++) {
            char symbol = packedString.charAt(i);

            if (']' == symbol) {
                SbAndDigit previousSbAndRepeatCount = stack.pop();
                StringBuilder prevSb = previousSbAndRepeatCount.sb;
                prevSb.append(sb.toString().repeat(previousSbAndRepeatCount.digit));
                sb = prevSb;
            } else if ('[' == symbol) {
                stack.push(new SbAndDigit(sb, digit));
                sb = new StringBuilder();
            } else if (Character.isDigit(symbol)) {
                digit = Integer.parseInt("" + symbol);
            } else {
                sb.append(symbol);
            }
        }

        return sb.toString();
    }

    private static class SbAndDigit {

        StringBuilder sb;
        int digit;

        public SbAndDigit(StringBuilder sb, int digit) {
            this.sb = sb;
            this.digit = digit;
        }
    }
}
