package MultiThreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {

    private void longRunningTask() {
        try {
            Thread.sleep(50000);
        } catch(Exception e) {
            
        }   
    }

    public static void main(String args[]) {
        ExecutorService pool = Executors.newFixedThreadPool(120);
        ThreadPoolExample tpe = new ThreadPoolExample();

        for (int i=0; i<10; i++) {
            pool.execute(() -> {
                Thread.currentThread().setName("ThreadPoolExample");
                tpe.longRunningTask();
            });
        }
        pool.shutdown();
    }
}

// $(jps | grep ThreadPoolExample | awk '{print $1}')
// ps M $(jps | grep ThreadPoolExample | awk '{print $1}' | grep "ThreadPoolExample") | tail -n+2 | wc -l