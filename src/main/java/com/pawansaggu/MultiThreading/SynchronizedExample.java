package MultiThreading;

public class SynchronizedExample {
    public static void main(String args[]) throws InterruptedException{
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2(thread1);
        Thread3 thread3 = new Thread3(thread1);
        thread1.start();
        thread2.start();
        Thread.sleep(100);
        thread3.start();
    }
}

class Thread1 extends Thread {
    @Override
    public void run() {
        synchronized(this) {
            System.out.println(Thread.currentThread().getName()+" starts");
            for(long i=0; i<1000000000000000000L; i++) {
            }
            try {
                this.wait();
            } catch(Exception e) {
                //
            }
            for(long i=0; i<1000000000000000000L; i++) {
            }
            System.out.println(Thread.currentThread().getName()+" notified");
            
        } 
    }
}

class Thread2 extends Thread {
    private Thread referenceObject;

    Thread2(Thread referenceObject) {
        this.referenceObject = referenceObject;
    }

    @Override
    public void run() {
        synchronized(this.referenceObject) {
            System.out.println(Thread.currentThread().getName()+" starts");
            try {
                this.referenceObject.wait();
            } catch(Exception e) {
                //
            }
            System.out.println(Thread.currentThread().getName()+" notified");
        } 
    }
}

class Thread3 extends Thread {
    private Thread referenceObject;

    Thread3(Thread referenceObject) {
        this.referenceObject = referenceObject;
    }

    @Override
    public void run() {
        synchronized(this.referenceObject) {
            System.out.println(Thread.currentThread().getName()+" starts");
            try {
                this.referenceObject.notify();
            } catch(Exception e) {
                //
            }
            for(long i=0; i<1000000000000000000L; i++) {
            }
            System.out.println(Thread.currentThread().getName()+" notified");
        } 
    }
}
