package RateLimiter.LeakyBucket;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

import RateLimiter.Options;
import RateLimiter.RateLimiter;

public class LeakyBucket implements RateLimiter{
    private int capacity;
    private Queue<Integer>queue;
    private int rate;
    private volatile Instant timeStamp;
    private Semaphore semaphore;
    private volatile int size;
    private int interval = 1;

    public LeakyBucket(int capacity, int rate) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
        this.timeStamp = Instant.now();
        this.semaphore = new Semaphore(1);
        this.size = 0;
        this.rate = 2;
    }

    private void leak(Instant currentTimeStamp) {
        Duration duration = Duration.between(this.timeStamp, currentTimeStamp);
        long durationInSeconds = duration.getSeconds();
        long dataToRemove = (durationInSeconds / interval) * rate;
        
        while (dataToRemove > 0 && !this.queue.isEmpty()) {
            long removedData = this.queue.poll();
    
            if (dataToRemove >= removedData) {
                dataToRemove -= removedData;
                this.size -= removedData;
            } else {
                this.size -= dataToRemove;

                queue.add((int)(removedData - dataToRemove));

                dataToRemove = 0;
            }
        }
    }

    public boolean allowRequest(Options options) {
        Instant currentTimeStamp = Instant.now();
        int dataSize = options.getDataSize();

        try {
            semaphore.acquire();

            this.leak(currentTimeStamp);

            if (this.size + dataSize <= capacity) {
                this.timeStamp = Instant.now();
                this.queue.add(dataSize);
                this.size += dataSize;
                
                return true;
            } 

            return false;
        } catch (Exception  e) {
            return true;
        } finally {
            semaphore.release();
        }
    }
}
