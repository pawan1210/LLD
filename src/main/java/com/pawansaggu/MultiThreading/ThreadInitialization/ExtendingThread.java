package MultiThreading.ThreadInitialization;

public class ExtendingThread {
    public static class MyThread extends Thread {
        public void run() {
            System.out.println("running inside a thread");
        }
    }

    public static void main (String args[]) {
        ExtendingThread.MyThread myThread = new ExtendingThread.MyThread();

        myThread.start();
    }
}
