package ya.test.sprint7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * Гуляя по одному из островов Алгосского архипелага, Гоша набрёл на пещеру, в которой лежат кучи золотого песка. К
 * счастью, у Гоши есть с собой рюкзак грузоподъёмностью до M килограмм, поэтому он может унести с собой какое-то
 * ограниченное количество золота.
 * <p>
 * Всего золотых куч n штук, и все они разные. В куче под номером i содержится mi килограммов золотого песка, а
 * стоимость одного килограмма — ci алгосских франков.
 * <p>
 * Помогите Гоше наполнить рюкзак так, чтобы общая стоимость золотого песка в пересчёте на алгосские франки была
 * максимальной.
 * <p>
 * Формат ввода В первой строке задано целое число M — грузоподъёмность рюкзака Гоши (0 ≤ M ≤ 108).
 * <p>
 * Во второй строке дано количество куч с золотым песком — целое число n (1 ≤ n ≤ 105).
 * <p>
 * В каждой из следующих n строк описаны кучи: i-ая куча задаётся двумя целыми числами ci и mi, записанными через пробел
 * (1 ≤ ci ≤ 107, 1 ≤ mi ≤ 108).
 * <p>
 * Формат вывода Выведите единственное число —– максимальную сумму (в алгосских франках), которую Гоша сможет вынести из
 * пещеры в своём рюкзаке.
 */
public class C_Золотая_лихорадка {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int maxKg = readInt(reader);
            int kuchasCount = readInt(reader);
            Kucha[] kuchas = readIntArrayWithSize(kuchasCount, reader);
            Arrays.sort(kuchas, Comparator.comparingInt(Kucha::getCost).reversed());

            long maximum = 0;
            int alreadyTakeKg = 0;

            for (Kucha kucha : kuchas) {
                if (alreadyTakeKg >= maxKg) {
                    break;
                }
                for (int i = 0; i < kucha.massa; i++) {
                    if (alreadyTakeKg < maxKg) {
                        maximum += kucha.cost;
                        alreadyTakeKg++;
                    } else {
                        break;
                    }
                }
            }

            writer.write(String.valueOf(maximum));
            writer.flush();
        }

    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static Kucha[] readIntArrayWithSize(int size, BufferedReader reader) throws IOException {

        final Kucha[] result = new Kucha[size];

        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            result[i] = new Kucha(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        return result;
    }

    public static class Kucha {

        int cost;
        int massa;

        public Kucha(int cost, int massa) {
            this.cost = cost;
            this.massa = massa;
        }

        public int getCost() {
            return cost;
        }

        public int getMassa() {
            return massa;
        }
    }
}
