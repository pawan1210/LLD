package MultiThreading.ZeroEvenOdd;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ZeroEvenOdd {
    private int n;
    private volatile boolean canPrintZero;
    private volatile int currentNumber;

    private Lock lock;
    private Condition semZero;
    private Condition semEven;
    private Condition semOdd;

    public ZeroEvenOdd(int n) {
        this.lock = new ReentrantLock();
        this.semZero = lock.newCondition();
        this.semEven = lock.newCondition();
        this.semOdd = lock.newCondition();
        this.n = n;
        this.currentNumber = 1;
        this.canPrintZero = true;
    }

    public void zero() throws InterruptedException {
        this.lock.lock();
        while (currentNumber <= n) {
            while (!this.canPrintZero) {
                try {
                    semZero.await();
                } catch (Exception e) {
    
                }
            }

            if (currentNumber <= n) {
                System.out.println(0);

                this.canPrintZero = false;
    
                if (currentNumber %2 == 0) {
                    semEven.signal();
                } else {
                    semOdd.signal();
                }
            }

        }
        
        semOdd.signal();
        semEven.signal();
        this.lock.unlock();
    }

    public void even() throws InterruptedException {
        this.lock.lock();
        while (currentNumber <= n) {
            while (this.canPrintZero && currentNumber <= n) {
                semEven.await();
            }

            if (currentNumber <= n && currentNumber %2 == 0) {
                System.out.println(currentNumber);
                this.currentNumber += 1;
                this.canPrintZero = true;
    
                semZero.signal();
            }
        }
        this.lock.unlock();
    }

    public void odd() throws InterruptedException{
        this.lock.lock();
        while (currentNumber <= n) {
            while (this.canPrintZero && currentNumber <= n) {
                semOdd.await();
            }

            if (currentNumber <= n && currentNumber %2 == 1) {
                System.out.println(currentNumber);
                this.currentNumber += 1;
                this.canPrintZero = true;
    
                semZero.signal();
            }
        }
        this.lock.unlock();
    }
}


class Main {
    static class ThreadZero implements Runnable{
        private ZeroEvenOdd instance;

        ThreadZero(ZeroEvenOdd instance) {
            this.instance = instance;
        }

        public void run(){
            try {
                instance.zero();
            } catch (Exception e) {

            }   
        }
    }

    static class ThreadOdd implements Runnable{
        private ZeroEvenOdd instance;

        ThreadOdd(ZeroEvenOdd instance) {
            this.instance = instance;
        }

        public void run(){
            try {
                instance.odd();
            } catch (Exception e) {
               
            }
        }
    }

    static class ThreadEven implements Runnable{
        private ZeroEvenOdd instance;

        ThreadEven(ZeroEvenOdd instance) {
            this.instance = instance;
        }

        public void run(){
            try {
                instance.even();
            } catch (Exception e) {

            }
            
        }
    }
    public static void main(String args[]){
        ZeroEvenOdd instance = new ZeroEvenOdd(50);
        Thread thread1 = new Thread(new ThreadZero(instance));
        Thread thread2 = new Thread(new ThreadOdd(instance));
        Thread thread3 = new Thread(new ThreadEven(instance));

        thread3.start();
        thread1.start();
            thread2.start();
            
            
        try {
            
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (Exception e) {

        }
    }
}