package ya.test.sprint7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * Дано количество учебных занятий, проходящих в одной аудитории. Для каждого из них указано время начала и конца. Нужно
 * составить расписание, в соответствии с которым в классе можно будет провести как можно больше занятий.
 * <p>
 * Если возможно несколько оптимальных вариантов, то выведите любой. Возможно одновременное проведение более чем одного
 * занятия нулевой длительности.
 * <p>
 * Формат ввода В первой строке задано число занятий. Оно не превосходит 1000. Далее для каждого занятия в отдельной
 * строке записано время начала и конца, разделённые пробелом. Время задаётся одним целым числом h, если урок
 * начинается/заканчивается ровно в h часов. Если же урок начинается/заканчивается в h часов m минут, то время
 * записывается как h.m. Гарантируется, что каждое занятие начинается не позже, чем заканчивается. Указываются только
 * значащие цифры.
 * <p>
 * Формат вывода Выведите в первой строке наибольшее число уроков, которое можно провести в аудитории. Далее выведите
 * время начала и конца каждого урока в отдельной строке в порядке их проведения.
 */
public class B_Расписание {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            Lesson[] lessons = readIntArrayWithSize(n, reader);
            Arrays.sort(lessons, Comparator.comparingDouble(Lesson::getEnd).thenComparingDouble(Lesson::getStart));

            ArrayList<Lesson> schedule = new ArrayList<>();
            Lesson previous = new Lesson(0.0, 0.0);

            for (Lesson lesson : lessons) {
                if (lesson.start >= previous.end) {
                    previous = lesson;
                    schedule.add(lesson);
                }
            }

            //Попробуй выбирать мероприятие, которое ещё не началось, но закончится раньше остальных.

            writer.write(schedule.size() + "\n");

            DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.ENGLISH));

            for (Lesson lesson : schedule) {
                writer.write(df.format(lesson.start) + " " + df.format(lesson.end) + "\n");
            }

            writer.flush();
        }

    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static Lesson[] readIntArrayWithSize(int size, BufferedReader reader) throws IOException {

        final Lesson[] result = new Lesson[size];

        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            result[i] = new Lesson(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
        }

        return result;
    }

    public static class Lesson {

        double start;
        double end;

        public Lesson(double start, double end) {
            this.start = start;
            this.end = end;
        }

        public double getStart() {
            return start;
        }

        public double getEnd() {
            return end;
        }
    }
}
