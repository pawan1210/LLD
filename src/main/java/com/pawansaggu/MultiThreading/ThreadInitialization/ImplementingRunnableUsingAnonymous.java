package MultiThreading.ThreadInitialization;

public class ImplementingRunnableUsingAnonymous {
    public static void main(String args[]) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("running inside a thread");
            }
        };

        Thread myThread = new Thread(runnable);

        myThread.start();
    }
}
