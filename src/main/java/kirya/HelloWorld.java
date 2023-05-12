package kirya;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

public class HelloWorld implements Inter {

    public HelloWorld(int a) {

    }

    public static void main(String[] args) {
//        NumberContainer<AI> number1 = new NumberContainer<>(new AI());
//        NumberContainer number5 = new NumberContainer(new AI());
//        number5.print();


//        List<Integer> ints = new LinkedList<Integer>();
        List<Integer> ints = new ArrayList<Integer>();
        ints.add(1);
        ints.add(2);
//        List<? extends Number> nums = ints;
//        nums.add(1);
//        nums.add(new Double(3.14)); // compile-time error

/*
        ints.forEach(number -> {
            if (number.equals(1)) {
                ints.remove(1);
            } else {
//                ints.add(4);
            }
        });
*/

        for (Integer number : ints) {
            System.out.println(number);
            if (number.equals(1)) {
                ints.remove((Integer)1);
                ints.remove((Integer)2);
            } else {
//                ints.add(4);
            }
        }

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 5, 100L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

            }
        });

    }

    @Override
    public Integer doWork() {
        return null;
    }

    public static class NumberContainer<T extends AI & Comparable<AI>> {

        private T number;

        public NumberContainer(T number) {
            this.number = number;
        }

        public void print() {
            int i = number.compareTo(new AI());
            System.out.println(number);
        }

        public static <Q extends Integer> void doW(Q ads) {

        }
    }

    public static class Container {
        public Set<Asd> asdHashSet = EnumSet.allOf(Asd.class);
        public String asd = "";
        public  List<? extends Integer> field;

        public static <T extends Number> T getFirst(List<? super Number> list) {
//            super - то это consumer. Он только принимает, а предоставить ничего не может.
            boolean add = list.add(new Double(3));
            Object object = list.get(0); //прочитать обджек для супер - исключение

            return null;
        }

        public static <T extends Number> T getFirst1(List<? extends Number> list) {
//            list.add(1); // Низя
            list.add(null); // добавить null для extends  - исключение
            //extends - продюсирует элементы из контейнера т.е. из него можно читать
            Number number = list.get(0);


            return null;
        }
    }

    public enum Asd {
        QWE,
        ASD
    }

    public static class AI implements Comparable<AI> {
        Object doWork(String s) throws Exception {
            return s.length();
        }

        @Override
        public int compareTo(@NotNull AI o) {
            return 0;
        }
    }
}
