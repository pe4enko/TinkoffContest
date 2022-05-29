package multithreading.sync;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Incrementer incrementer = new Incrementer(counter);
        Decrementer decrementer = new Decrementer(counter);

        incrementer.start();
        decrementer.start();

        incrementer.join();
        decrementer.join();

        System.out.println(counter.get());

    }
}
