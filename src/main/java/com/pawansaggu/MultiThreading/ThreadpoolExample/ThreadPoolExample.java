package MultiThreading.ThreadpoolExample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
    public static class Task implements Runnable {
        public void run() {
            System.out.println("task running");
        }
    }

    public static void main(String args[]) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i=0; i<10; i++) {
            executor.submit(new Task());
        }
        
        executor.shutdown();
    }
}
