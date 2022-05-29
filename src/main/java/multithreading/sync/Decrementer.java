package multithreading.sync;

public class Decrementer extends Thread{
    private Counter counter;

    public Decrementer(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            counter.dec();
        }
    }
}
