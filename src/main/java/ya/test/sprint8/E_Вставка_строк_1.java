package ya.test.sprint8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * У Риты была строка s, Гоша подарил ей на 8 марта ещё n других строк ti, 1≤ i≤ n. Теперь Рита думает, куда их лучше
 * поставить. Один из вариантов —– расположить подаренные строки внутри имеющейся строки s, поставив строку ti сразу
 * после символа строки s с номером ki (в частности, если ki=0, то строка вставляется в самое начало s).
 * <p>
 * Помогите Рите и определите, какая строка получится после вставки в s всех подаренных Гошей строк.
 * <p>
 * Формат ввода В первой строке дана строка s. Строка состоит из строчных букв английского алфавита, не бывает пустой и
 * её длина не превышает 105 символов.
 * <p>
 * Во второй строке записано количество подаренных строк — натуральное число n, 1 ≤ n ≤ 105.
 * <p>
 * В каждой из следующих n строк через пробел записаны пары ti и ki. Строка ti состоит из маленьких латинских букв и не
 * бывает пустой. ki — целое число, лежащее в диапазоне от 0 до |s|. Все числа ki уникальны. Гарантируется, что
 * суммарная длина всех строк ti не превосходит 105.
 * <p>
 * Формат вывода Выведите получившуюся в результате вставок строку.
 */
public class E_Вставка_строк_1 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String originString = reader.readLine();
            int n = Integer.parseInt(reader.readLine());

            TreeMap<Integer, String> map = new TreeMap<>();
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(reader.readLine());
                String s = st.nextToken();
                int insertPosition = Integer.parseInt(st.nextToken());

                map.put(insertPosition, s);
            }

            int i = 0;
            for (Entry<Integer, String> entry : map.entrySet()) {
                Integer insertPosition = entry.getKey();
                String str = entry.getValue();

                while (insertPosition > i) {
                    writer.write(originString.charAt(i));
                    i++;
                }

                writer.write(str);
            }

            while (i < originString.length()) {
                writer.write(originString.charAt(i));
                i++;
            }

            writer.flush();
        }
    }
}
