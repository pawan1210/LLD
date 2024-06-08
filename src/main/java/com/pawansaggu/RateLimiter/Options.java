package RateLimiter;

public class Options {
    private int requiredTokens;

    public Options(int requiredTokens) {
        this.requiredTokens = requiredTokens;
    }

    public int getRequiredTokens() {
        return this.requiredTokens;
    }
}
