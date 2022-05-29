package multithreading.sync;

public class Counter {
    private Integer i = 0;

    public synchronized void inc() {
        try {
            System.out.println("inc");
            Thread.sleep(10000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i++;
    }

    public synchronized void dec() {
        System.out.println("dec");
        i--;
    }

    public Integer get() {
        return i;
    }
}
