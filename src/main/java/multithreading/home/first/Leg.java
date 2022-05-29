package multithreading.home.first;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Leg implements Runnable {

    private final String name;
    private final CyclicBarrier barrier;
    private final Object mutex1;
    private final Object mutex2;
    private Semaphore sem;

    public Leg(String name, CyclicBarrier barrier, Object mutex1, Object mutex2, Semaphore sem) {
        this.name = name;
        this.barrier = barrier;
        this.mutex1 = mutex1;
        this.mutex2 = mutex2;
        this.sem = sem;
    }

    @Override
    public void run() {
        while (true) {

            try {
                sem.acquire();

                System.out.println(Thread.currentThread().getName() + ":" + name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                sem.release();
            }

//            synchronized (mutex1) {
//                synchronized (mutex2) {
//                    System.out.println(Thread.currentThread().getName() + ":" + name);
//                }
//            }
//            try {
//                barrier.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (BrokenBarrierException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + ":" + name);
        }
    }

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(2);
        Object mutex1 = new Object();
        Object mutex2 = new Object();
        Semaphore sem = new Semaphore(1, true);
//        while (true) {
//            CompletableFuture.runAsync(new Leg("left", barrier))
//                    .thenRun(new Leg("right", barrier));
//        }
        CompletableFuture.allOf(
                CompletableFuture.runAsync(new Leg("left", barrier, mutex1, mutex2, sem )),
                CompletableFuture.runAsync(new Leg("right", barrier, mutex1, mutex2, sem))
        ).join();
    }
}