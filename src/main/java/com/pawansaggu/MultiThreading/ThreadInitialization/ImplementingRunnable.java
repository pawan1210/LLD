package MultiThreading.ThreadInitialization;

public class ImplementingRunnable {
    public static class MyThread implements Runnable {
        public void run() {
            String threadName = Thread.currentThread().getName();

            System.out.println("running " + threadName);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //
            }

            System.out.println("finished" + threadName);
        }
    }

    public static void main (String args[]) {
        Thread thread = new Thread(new MyThread());
        thread.start();

        Thread newThread2 = new Thread(new MyThread());

        newThread2.start();
    }
}
