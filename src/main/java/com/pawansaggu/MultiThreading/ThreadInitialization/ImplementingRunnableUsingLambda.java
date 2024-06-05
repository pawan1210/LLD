package MultiThreading.ThreadInitialization;

public class ImplementingRunnableUsingLambda {
    public static void main (String args[]) {
        Runnable runnable = () -> {
            System.out.println("running inside a thread");
        };

        Thread myThread = new Thread(runnable);

        myThread.start();
    }
}
