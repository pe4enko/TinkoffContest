package ya.test.sprint4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.StringTokenizer;

/**
 * В компании, где работает Тимофей, заботятся о досуге сотрудников и устраивают различные кружки по интересам. Когда
 * кто-то записывается на занятие, в лог вносится название кружка.
 * <p>
 * По записям в логе составьте список всех кружков, в которые ходит хотя бы один человек.
 * <p>
 * Формат ввода В первой строке даётся натуральное число n, не превосходящее 10 000 –— количество записей в логе.
 * <p>
 * В следующих n строках —– названия кружков.
 * <p>
 * Формат вывода Выведите уникальные названия кружков по одному на строке, в порядке появления во входных данных.
 */
public class D_Кружки {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);

            HashMap<String, String> circles = new HashMap<>();

            for (int i = 0; i < n; i++) {
                String s = reader.readLine();

                if (!circles.containsKey(s)) {
                    writer.write(s + "\n");
                    circles.put(s, s);
                }
            }

            writer.flush();
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
