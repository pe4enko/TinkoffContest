package ya.test.sprint8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Представьте, что вы работаете пограничником и постоянно проверяете документы людей по записи из базы. При этом
 * допустима ситуация, когда имя человека в базе отличается от имени в паспорте на одну замену, одно удаление или одну
 * вставку символа. Если один вариант имени может быть получен из другого удалением одного символа, то человека
 * пропустят через границу. А вот если есть какое-либо второе изменение, то человек грустно поедет домой или в
 * посольство.
 * <p>
 * Например, если первый вариант —– это «Лена», а второй — «Лера», то девушку пропустят. Также человека пропустят, если
 * в базе записано «Коля», а в паспорте — «оля».
 * <p>
 * Однако вариант, когда в базе числится «Иннокентий», а в паспорте написано «ннакентий», уже не сработает. Не пропустят
 * также человека, у которого в паспорте записан «Иинннокентий», а вот «Инннокентий» спокойно пересечёт границу.
 * <p>
 * Напишите программу, которая сравнивает имя в базе с именем в паспорте и решает, пропускать человека или нет. В случае
 * равенства двух строк — путешественника, естественно, пропускают.
 * <p>
 * Формат ввода В первой строке дано имя из паспорта.
 * <p>
 * Во второй строке —- имя из базы.
 * <p>
 * Обе строки состоят из строчных букв английского алфавита. Размер каждой строки не превосходит 100 000 символов.
 * <p>
 * Формат вывода Выведите «OK», если человека пропустят, или «FAIL» в противном случае.
 */
public class B_Пограничный_контроль {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String s1 = reader.readLine();
            String s2 = reader.readLine();

            //Если отличаются больше чем на 1 символ.
            int s1l = s1.length();
            int s2l = s2.length();
            if (s1l - s2l > 1 || s2l - s1l > 1) {
                writer.write("FAIL");
                return;
            }

            int i = 0;
            int j = 0;
            boolean alreadyDoOperation = false;

            while (i < s1.length() && j < s2.length()) {
                char c1 = s1.charAt(i);
                char c2 = s2.charAt(j);

                if (c1 != c2) {
                    if (alreadyDoOperation) {
                        writer.write("FAIL");
                        return;
                    }
                    alreadyDoOperation = true;

                    if (s1l == s2l) {
                        //если строки одинаковой длины, то только replace
                    } else if (s1l < s2l) {
                        //тогда можем вставить в s1 строку
                        i--;
                    } else {
                        j--;
                    }
                }
                i++;
                j++;
            }

            writer.write("OK");
            writer.flush();
        }
    }
}
