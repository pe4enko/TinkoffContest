package multithreading.home.first;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Leg2 implements Runnable {

    private final String name;
    private final ReentrantLock locker;
    private final Condition condition;

    public Leg2(String name, ReentrantLock locker, Condition condition) {
        this.name = name;
        this.locker = locker;
        this.condition = condition;
    }

    @Override
    public void run() {
//        while (true) {
//
//            locker.lock();
//            try {
//                while () {
//                    condition.await();
//                }
//
//                System.out.println(Thread.currentThread().getName() + ":" + name);
//
//                condition.signal();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                locker.unlock();
//            }
//        }
    }

    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1, true);
//        CompletableFuture.allOf(
//                CompletableFuture.runAsync(new Leg2("left", locker, condition)),
//                CompletableFuture.runAsync(new Leg2("right", locker, condition))
//        ).join();
    }
}