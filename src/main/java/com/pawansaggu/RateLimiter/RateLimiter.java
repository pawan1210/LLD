package RateLimiter;

public interface RateLimiter {
    public boolean allowRequest(Options options);
}

