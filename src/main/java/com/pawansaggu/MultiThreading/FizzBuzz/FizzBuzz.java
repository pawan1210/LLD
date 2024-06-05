package MultiThreading.FizzBuzz;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class FizzBuzz {
    private int n;
    private volatile int count;
    private Condition sFizz;
    private Condition sBuzz;
    private Condition sFizzBuzz;
    private Condition sNumber;
    private Lock lock;

    public FizzBuzz(int n) {
        this.n = n;
        this.count = 1;
        this.lock = new ReentrantLock();
        this.sFizz = lock.newCondition();
        this.sBuzz = lock.newCondition();
        this.sFizzBuzz = lock.newCondition();
        this.sNumber = lock.newCondition();
    }

    private void releaseSemaphore() {
        if (this.count % 3 == 0 && this.count % 5 == 0) {
            System.out.println("release fizzbuzz");
            this.sFizzBuzz.signal();
        } else if (this.count % 3 == 0 && this.count % 5 != 0) {
            System.out.println("release fizz");
            this.sFizz.signal();
        } else if (this.count % 3 != 0 && this.count % 5 == 0) {
            System.out.println("release buzz");
            this.sBuzz.signal();
        } else if (this.count % 3 != 0 || this.count % 5 != 0) {
            System.out.println("release number");
            this.sNumber.signal();
        }
    }

    private void releaseAll() {
        System.out.println("inside release all");
        this.sFizzBuzz.signal();
        this.sFizz.signal();
        this.sBuzz.signal();
        this.sNumber.signal();
        System.out.println("outside release all");
    }

    // printFizz.run() outputs "fizz".
    public void fizz() throws InterruptedException {
        this.lock.lock();

        while (this.count <= n) {
            while (!(this.count % 3 == 0 && this.count % 5 != 0)) {
                if (this.count <= n) {
                    System.out.println("inside fizz");
                    sFizz.await();
                } else {
                    break;
                }
            }

             System.out.println("outside fizz");
            if (this.count <= n) {
                System.out.println("acquiring fizz" + this.count);
               System.out.println("fizz");
                this.count++;
                releaseSemaphore();
            } else {
                break;
            }
        }
        this.releaseAll();
        this.lock.unlock();
    }

    // printBuzz.run() outputs "buzz".
    public void buzz() throws InterruptedException {
        this.lock.lock();

        while (this.count <= this.n) {
            while (!(this.count % 3 != 0 && this.count % 5 == 0)) {
                if (this.count <= n) {
                     System.out.println("inside buzz");
                    sFizz.await();
                } else {
                    break;
                }
            }

             System.out.println("outside fizz");
            if (this.count <= n) {
                System.out.println("acquiring buzz"+ this.count);
                System.out.println("buzz");
                this.count++;
                releaseSemaphore();
            } else {
                break;
            }
        }

        this.releaseAll();
        this.lock.unlock();
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz() throws InterruptedException {
        this.lock.lock();

        while (this.count <=n ) {
            while (!(this.count % 3 == 0 && this.count % 5 == 0)){
                if (this.count <= n) {
                    System.out.println("inside fizzbuzz");
                    sFizz.await();
                } else {
                    break;
                }
            }

            System.out.println("outside fizzbuzz");
            if (this.count <= n) {
                System.out.println("acquiring fizzbuzz"+ this.count);
                System.out.println("fizzbuzz");
                this.count++;
                releaseSemaphore();
            } else {
                break;
            }
        }

        this.releaseAll();
        this.lock.unlock();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number() throws InterruptedException {
        this.lock.lock();

        while (this.count <=n) {
            while (!(this.count % 3 != 0 && this.count % 5 != 0)) {
                if (this.count <= n) {
                    System.out.println("inside number");
                    sFizz.await();
                } else {
                    break;
                }
            }

             System.out.println("outside number");
            if (this.count <= n) {
                System.out.println("acquiring number"+ this.count);
                // printNumber.accept(this.count);
                System.out.println(this.count);
                this.count++;
                releaseSemaphore();
            } else {
                break;
            }
        }

        this.releaseAll();
        this.lock.unlock();
    }
}

class Main {
    static class ThreadFizz implements Runnable{
        private FizzBuzz instance;

        ThreadFizz(FizzBuzz instance) {
            this.instance = instance;
        }

        public void run(){
            try {
                instance.fizz();
            } catch (Exception e) {

            }   
        }
    }

    static class ThreadBuzz implements Runnable{
        private FizzBuzz instance;

        ThreadBuzz(FizzBuzz instance) {
            this.instance = instance;
        }

        public void run(){
            try {
                instance.buzz();
            } catch (Exception e) {
               
            }
        }
    }

    static class ThreadFizzBuzz implements Runnable{
        private FizzBuzz instance;

        ThreadFizzBuzz(FizzBuzz instance) {
            this.instance = instance;
        }

        public void run(){
            try {
                instance.fizzbuzz();
            } catch (Exception e) {

            }
            
        }
    }

    static class ThreadNumber implements Runnable{
        private FizzBuzz instance;

        ThreadNumber(FizzBuzz instance) {
            this.instance = instance;
        }

        public void run(){
            try {
                instance.number();
            } catch (Exception e) {

            }
            
        }
    }
    public static void main(String args[]){
        FizzBuzz instance = new FizzBuzz(4);
        Thread thread1 = new Thread(new ThreadFizz(instance));
        Thread thread2 = new Thread(new ThreadBuzz(instance));
        Thread thread3 = new Thread(new ThreadFizzBuzz(instance));
        Thread thread4 = new Thread(new ThreadNumber(instance));

        thread3.start();
        thread1.start();
        thread2.start();
        thread4.start();
            
            
        try {
            
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (Exception e) {

        }
    }
}