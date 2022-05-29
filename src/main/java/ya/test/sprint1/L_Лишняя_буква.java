package ya.test.sprint1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Васе очень нравятся задачи про строки, поэтому он придумал свою. Есть 2 строки s и t, состоящие только из строчных
 * букв. Строка t получена перемешиванием букв строки s и добавлением 1 буквы в случайную позицию. Нужно найти
 * добавленную букву.
 * <p>
 * Формат ввода На вход подаются строки s и t, разделённые переносом строки. Длины строк не превосходят 1000 символов.
 * Строки не бывают пустыми.
 * <p>
 * Формат вывода Выведите лишнюю букву.
 */
public class L_Лишняя_буква {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String s = reader.readLine();
            String t = reader.readLine();

            int sSum = s.chars().sum();
            int tSum = t.chars().sum();

            int c = tSum - sSum;

            writer.write((char) c);
            writer.flush();
        }
    }
}
