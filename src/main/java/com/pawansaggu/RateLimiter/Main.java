package RateLimiter;

// import RateLimiter.TokenBucket.TokenBucket;
import RateLimiter.LeakyBucket.LeakyBucket;

class Request implements Runnable {
    private RateLimiter limiter;

    public Request(RateLimiter limiter) {
        this.limiter = limiter;
    }

    public void run() {
        int count = 1000000000;

        while (count-- > 0) {
            boolean requestAllowed = limiter.allowRequest(new Options().setDataSize(2));
            String name = Thread.currentThread().getName();

            if (requestAllowed) {
                System.out.println("Request allowed by " + name);
                continue;
            } else {
                // System.out.println("Request denied by "+ name);
            }
        }
       
    }
}

public class Main {
    public static void main(String args[]) {
        // RateLimiter limiter = new TokenBucket(5, 1);
        RateLimiter limiter = new LeakyBucket(5, 2);
        Thread thread1 = new Thread(new Request(limiter));
        Thread thread2 = new Thread(new Request(limiter));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (Exception e) {
            
        }
    }
}
