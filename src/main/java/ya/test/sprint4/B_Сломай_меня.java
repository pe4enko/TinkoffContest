package ya.test.sprint4;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

/**
 * Гоша написал программу, которая сравнивает строки исключительно по их хешам. Если хеш равен, то и строки равны.
 * Тимофей увидел это безобразие и поручил вам сломать программу Гоши, чтобы остальным неповадно было.
 * <p>
 * В этой задаче вам надо будет лишь найти две различные строки, которые для заданной хеш-функции будут давать
 * одинаковое значение.
 * <p>
 * Гоша использует следующую хеш-функцию:
 * <p>
 * <p>
 * для a = 1000 и m = 123 987 123. В данной задаче необходимо использовать в качестве значений отдельных символов их
 * коды в таблице ASCII.
 * <p>
 * Формат ввода В задаче единственный тест без ввода
 * <p>
 * Формат вывода Отправьте две строки, по одной в строке. Строки могут состоять только из маленьких латинских букв и не
 * должны превышать в длину 1000 знаков каждая. Код отправлять не требуется. Строки из примера использовать нельзя.
 * <p>
 * Пример вывода:
 * <p>
 * ezhgeljkablzwnvuwqvp
 * <p>
 * gbpdcvkumyfxillgnqrv
 */
public class B_Сломай_меня {

    public static void main(String[] args) throws IOException {

        int a = 1000;
        int m = 123_987_123;
//        String s = "aaaaaa"; // 44628207
//        String s = "sgfnyd"; //41388933

        HashMap<Long, String> collision = new HashMap<>();

        int iter=0;
        while (true) {
            String s = givenUsingJava8_whenGeneratingRandomAlphanumericString_thenCorrect();

            long hash = 0;
            long stepen = 1;
            int n = s.length();

            for (int i = n - 1; i >= 0; i--) {
                char c = s.charAt(i);
                hash = (hash + ((c % m) * stepen) % m) % m;
                stepen = (stepen * (a % m)) % m;
            }

//        System.out.println(hash);
            String col = collision.get(hash);
            if (col == null) {
                collision.put(hash, s);
            } else {
                System.out.println(col);
                System.out.println(s);
                System.out.println(iter);
                return;
            }
            iter++;
        }
    }

    public static String givenUsingJava8_whenGeneratingRandomAlphanumericString_thenCorrect() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
//                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)) //97-122
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

    }
}