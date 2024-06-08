package RateLimiter.FixedInternal;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Semaphore;

import RateLimiter.Options;
import RateLimiter.RateLimiter;

public class FixedInterval implements RateLimiter {    
    private Instant timeStamp;
    private int capacity;
    private Semaphore semaphore;
    private int interval;
    private int counter;

    public FixedInterval(int capacity, int interval) {
        this.timeStamp = Instant.now();
        this.capacity = capacity;
        this.interval = interval;
        this.counter = 0;
        this.semaphore = new Semaphore(1);
    }

    public boolean allowRequest(Options options) {
        try {
            Instant currentTimeStamp = Instant.now();
            Duration duration = Duration.between(this.timeStamp, currentTimeStamp);
            long durationInSeconds = duration.getSeconds();
        
            if (durationInSeconds >= this.interval) {
                this.timeStamp = Instant.now();
                this.counter = 0;
            }

            if (this.counter < this.capacity) {
                this.counter++;

                return true;
            }

            return false;
        } catch (Exception e) {
            return false;
        } finally {
            semaphore.release();
        }
    }
}
