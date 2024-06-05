package MultiThreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

class ReentrantLockExample implements Runnable {
    private static ReentrantLock rlock = new ReentrantLock();
    private String taskName;

    public ReentrantLockExample(String taskName) {
        this.taskName = taskName;
    }

    public void sharedFunction() {
        boolean canAcquire = rlock.tryLock();
        if(canAcquire) {
            rlock.lock();
            System.out.println("Taskname -" + taskName + "acquired lock");
            rlock.unlock();
            rlock.unlock();
        } else {
            System.out.println("Taskname -" + taskName + "waiting for lock");
        }
    }
    public void run() {
        sharedFunction();
    }

    public static void main(String args[]) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Runnable r1 = new ReentrantLockExample("Task 1");
        Runnable r2 = new ReentrantLockExample("Task 2");
        pool.execute(r1);
        pool.execute(r2);
        pool.shutdown();
    }
}