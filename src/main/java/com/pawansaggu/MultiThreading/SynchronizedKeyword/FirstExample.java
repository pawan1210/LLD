package MultiThreading.SynchronizedKeyword;

public class FirstExample {
    public static class MyThread implements Runnable {
        private FirstExample instance;

        MyThread(FirstExample instance) {
            this.instance = instance;
        }

        public void run() {
            // this.instance.simulateLongRunningFunction();
            this.instance.simulateLongRunningCodeBlock(instance);
        }
    }

    public synchronized void simulateLongRunningFunction() {
        String threadName = Thread.currentThread().getName();
        Long ptr = 0L;

        System.out.println("Running :" + threadName);

        try {
            for (; ptr<1000000000; ptr++) {
                
            }
        } catch(Exception e) {

        }
    }

    public void simulateLongRunningCodeBlock(FirstExample instance) {
        String threadName = Thread.currentThread().getName();
        Long ptr = 0L;

        System.out.println("Running :" + threadName);

        synchronized(instance) {
            try {
                for (; ptr<1000000000; ptr++) {
                    
                }
            } catch(Exception e) {

            }
        }
    }

    public static void main(String[] args) {
        FirstExample exampleInstance = new FirstExample();
        Thread thread1 = new Thread(new FirstExample.MyThread(exampleInstance));
        Thread thread2 = new Thread(new FirstExample.MyThread(exampleInstance));
        Thread thread3 = new Thread(new FirstExample.MyThread(exampleInstance));

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
        } catch(Exception e) {

        }
        
    }
}

