package RateLimiter.SlidingWindow;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Semaphore;

import RateLimiter.Options;
import RateLimiter.RateLimiter;

public class SlidingWindow implements RateLimiter {
    private Semaphore semaphore;
    private int capacity;
    private int interval;
    private Deque<Instant>deque;

    public SlidingWindow(int capacity, int interval) {
        this.capacity = capacity;
        this.interval = interval;
        this.semaphore = new Semaphore(1);
        this.deque = new ArrayDeque<>();
    }

    private void removeRequests(Instant currentTimeStamp) {
        while (!this.deque.isEmpty()) {
            Duration duration = Duration.between(deque.getFirst(), currentTimeStamp);
            long durationInSeconds = duration.getSeconds();

            if (durationInSeconds >= this.interval) {
                deque.removeFirst();
            } else {
                break;
            }
        }
    }

    public boolean allowRequest(Options options) {
        try {
            semaphore.acquire();

            Instant currentTimestamp = Instant.now();

            removeRequests(currentTimestamp);

            if (this.deque.size() < this.capacity) {
                this.deque.addLast(currentTimestamp);

                return true;
            }

            return false;
        } catch (Exception e) {
            return true;
        } finally {
            semaphore.release();
        }
    }
}
