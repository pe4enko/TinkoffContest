package ya.test.sprint3final.b;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * https://contest.yandex.ru/contest/23815/run-report/69244516/ -- ПРИНЦИП РАБОТЫ -- Алгоритм применяет парадигму
 * "разделяй и властвуй". Процесс сортировки массива состоит из трех этапов. 1) Разделение - массив разбивается на два
 * массива, таких, что каждый элемент одно массива <= некоторого выбранного опорного значения, второй массив полностью
 * состоит из элементов, которые больше этого же опроного значения. 2) Властвование - Подмассивы сортируются с помощью
 * рекурсивного вызова разрабатываемого метода. 3) Комбинирование - комбинирование подмассивом в один общий массив, но
 * поскольку мы используем inPlace методику, когда элементы меняются местами в первоначальном массиве, то данный этап
 * выполнять нет необходимости.
 * <p>
 * Изначально в описании к задаче приводится разбор алгоритма с использованием методики inplace, но на мой взгляд
 * приведенный алгорит не корректен и реализовать его не удалось. В итоге пришлось открывать Кормена "Алгооритмы
 * построение и анализ" и откровенно говоря списывать решение от туда. Ни одно решени из интеренета не работало по тому
 * принципу который описан в задаче. Были решение с "дырой", когда самый крайний правый элемент используется как pivot и
 * на его место на каждом шаге сдвигаются элементы, мне такой подход показался не красивым. Потрачено достаточно много
 * времени я бы конечно еще хотел обсудить, решение из задачи, но так как "жесткий" дедлайн, то будет это списанное
 * решение.
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ -- Не понятно, что надо доказать в этом разделе ?
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ -- Наихудший случай имеет сложность O(n^2) такой сценарий возможен если выбирается неудачный
 * опорный элемент на всех шагах разбиения. Такой, что после разбиения все элементы оказываются в одном из подмассивом.
 * Но в среднем время работы будет O(n * log n) потому, что на каждом этапе мы уменьшаем кол-во сортируемых элементов на
 * 1, даже в худшем сценарии, т.к. каждое разбиение ставит опорный элемент на свое место. Худший случай - когда все
 * значения в массиве уже упорядочены и мы выбрали опорную точку минимальной, но а нашев случае все Стажеры сравниваются
 * по целым 3 параметрам и вероятность, что все стажеры будут упрядочены маловероятна. На каждом шаге будет выполняться
 * (n - 1) сравнений, кол-во шагов приблизительно будет logN. Таким образом средняя временная сложность алгоритмы n *
 * logN1.
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ -- В алгоритме применяется inplace модификация алгоритма, которая подразумевает, что
 * при разделении не выделяется дополнительная оперативная память. Таким образом пространственная сложность алгоритма
 * будет зависить только от кол-во участников. O(n) памяти.
 */
public class InternQuickSort {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int internsCount = readInt(reader);
            Intern[] interns = readInterns(internsCount, reader);

            sort(interns);

            for (Intern intern : interns) {
                writer.write(intern.login + "\n");
            }

            writer.flush();
        }
    }

    public static <T extends Comparable<? super T>> void sort(T[] arr) {
        sortRecursive(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<? super T>> void sortRecursive(T[] arr, int from, int to) {
        if (from >= to) {
            return;
        }

        //pivotPosition - уже на свое месте по этому ее сортировать не нужно и индекс pivotPosition - 1
        int pivotPosition = partition(arr, from, to);

        sortRecursive(arr, from, pivotPosition - 1);
        sortRecursive(arr, pivotPosition + 1, to);
    }

    private static <T extends Comparable<? super T>> int partition(T[] arr, int left, int right) {
        T pivot = arr[right];

        //указывает на крайний правый элемент из отрезка со значениями <= pivot
        int i = left - 1;

        //j всегда указывает на самый левый элемент из отрезка со значениями > pivot
        for (int j = left; j <= right - 1; j++) {
            if (arr[j].compareTo(pivot) <= 0) {
                i++;

                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, right);

        return i + 1;
    }

    private static <T extends Comparable<? super T>> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static Intern[] readInterns(int internsCount, BufferedReader reader) throws IOException {

        Intern[] interns = new Intern[internsCount];

        for (int i = 0; i < internsCount; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());

            String login = st.nextToken();
            int solvedTasks = Integer.parseInt(st.nextToken());
            int penalty = Integer.parseInt(st.nextToken());

            interns[i] = new Intern(login, solvedTasks, penalty);
        }

        return interns;
    }

    public static class Intern implements Comparable<Intern> {

        public final String login;
        public final int solvedTasks;
        public final int penalty;

        public Intern(String login, int solvedTasks, int penalty) {
            this.login = login;
            this.solvedTasks = solvedTasks;
            this.penalty = penalty;
        }

        /**
         * Тимофей решил сортировать таблицу результатов следующим образом: при сравнении двух участников выше будет
         * идти тот, у которого решено больше задач. При равенстве числа решённых задач первым идёт участник с меньшим
         * штрафом. Если же и штрафы совпадают, то первым будет тот, у которого логин идёт раньше в алфавитном
         * (лексикографическом) порядке.
         */
        @Override
        public int compareTo(Intern other) {
            //при сравнении двух участников выше будет идти тот, у которого решено больше задач
            int compareResult = -Integer.compare(this.solvedTasks, other.solvedTasks);
            if (compareResult != 0) {
                return compareResult;
            }

            //При равенстве числа решённых задач первым идёт участник с меньшим штрафом
            int compareResultPenalty = Integer.compare(this.penalty, other.penalty);
            if (compareResultPenalty != 0) {
                return compareResultPenalty;
            }

            // Если же и штрафы совпадают, то первым будет тот, у которого логин идёт раньше
            // в алфавитном (лексикографическом) порядке
            return this.login.compareTo(other.login);
        }
    }
}
