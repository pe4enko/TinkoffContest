package ya.test.sprint4;

import static java.lang.Integer.parseInt;
import static java.lang.Math.floorMod;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * //todo: ничего не понял полностью списано, потому что нет времени разбираться
 * Алла не остановилась на достигнутом –— теперь она хочет научиться быстро вычислять хеши произвольных подстрок данной
 * строки. Помогите ей!
 * <p>
 * На вход поступают запросы на подсчёт хешей разных подстрок. Ответ на каждый запрос должен выполняться за O(1).
 * Допустимо в начале работы программы сделать предподсчёт для дальнейшей работы со строкой.
 * <p>
 * Напомним, что полиномиальный хеш считается по формуле
 * <p>
 * <p>
 * В данной задаче необходимо использовать в качестве значений отдельных символов их коды в таблице ASCII.
 * <p>
 * Формат ввода В первой строке дано число a (1 ≤ a ≤ 1000) –— основание, по которому считается хеш. Во второй строке
 * дано число m (1 ≤ m ≤ 107) –— модуль. В третьей строке дана строка s (0 ≤ |s| ≤ 106), состоящая из больших и
 * маленьких латинских букв.
 * <p>
 * В четвертой строке дано число запросов t –— натуральное число от 1 до 105. В каждой из следующих t строк записаны
 * через пробел два числа l и r –— индексы начала и конца очередной подстроки. (1 ≤ l ≤ r ≤ |s|).
 * <p>
 * Формат вывода Для каждого запроса выведите на отдельной строке хеш заданной в запросе подстроки.
 */
public class C_Префиксные_хеши {

//    https://codeforces.com/blog/entry/17507
//    // deg[] = {1, P, P^2, P^3, ...}
//    // h[] = {0, s[0], s[0]*P + s[1], s[0]*P^2 + s[1]*P + s[2], ...}
//    unsigned long long h[n + 1], deg[n + 1];
//    h[0] = 0, deg[0] = 1;
//    for (int i = 0; i < n; i++) {
//        h[i + 1] = h[i] * P + s[i];
//        deg[i + 1] = deg[i] * P;
//    }
//    auto get_hash = [&]( int l, int r ) { // [l..r]
//        return h[r + 1] - h[l] * deg[r - l + 1];
//    };

    /**
     * Полиномиальный хеш можно эффективно вычислить с использованием метода Горнера:
     * https://ru.wikipedia.org/wiki/%D0%A1%D1%85%D0%B5%D0%BC%D0%B0_%D0%93%D0%BE%D1%80%D0%BD%D0%B5%D1%80%D0%B0
     */
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int a = readInt(reader);
            int m = readInt(reader);
            String s = reader.readLine();
            int t = readInt(reader);

            long[] deg = new long[s.length() + 1];
            long[] h = new long[s.length() + 1];

            h[0] = 0;
            deg[0] = 1;

            for (int i = 0; i < s.length(); i++) {
//                h[i + 1] = h[i] * a + s.charAt(i);
//                deg[i + 1] = deg[i] * a;
                h[i + 1] = ((h[i] * a) % m + s.charAt(i)) % m;
                deg[i + 1] = (deg[i] * a) % m;

            }

            for (int i = 0; i < t; i++) {
                StringTokenizer st = new StringTokenizer(reader.readLine());

                int l = parseInt(st.nextToken()) - 1;
                int r = parseInt(st.nextToken()) - 1;

                long hash = floorMod(h[r + 1] - h[l] * deg[r - l + 1], m);

                writer.write(hash + "\n");
            }

            writer.flush();
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return parseInt(reader.readLine());
    }
}
