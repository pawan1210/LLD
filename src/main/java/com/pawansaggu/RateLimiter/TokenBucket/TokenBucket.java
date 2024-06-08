package RateLimiter.TokenBucket;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Semaphore;

import RateLimiter.Options;
import RateLimiter.RateLimiter;

public class TokenBucket implements RateLimiter {
    private long capacity;
    private int rate;
    private volatile Instant timeStamp;
    private volatile long tokens;
    private Semaphore semaphore;


    public TokenBucket(long capacity, int rate) {
        this.capacity = capacity;
        this.rate = rate;
        this.timeStamp = Instant.now();
        this.tokens = capacity;
        this.semaphore = new Semaphore(1);
    }

    private void fillBucket(Instant currentTimeStamp) {
        Duration duration = Duration.between(this.timeStamp, currentTimeStamp);
        long durationInSeconds = duration.getSeconds();
        long tokensToAdd = rate * durationInSeconds;
        this.tokens = Math.min(capacity, this.tokens + tokensToAdd);
    }

    public boolean allowRequest(Options options) {
        int requiredTokens = options.getRequiredTokens();
        Instant currentTimeStamp = Instant.now();
        
        try {
            semaphore.acquire();
            this.fillBucket(currentTimeStamp);

            if (this.tokens - requiredTokens >= 0) {
                this.timeStamp = currentTimeStamp;
                this.tokens -= requiredTokens;

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
