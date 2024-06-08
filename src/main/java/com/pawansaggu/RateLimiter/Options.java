package RateLimiter;

public class Options {
    private int requiredTokens;
    private int dataSize;

    public Options() {
    }

    public Options setDataSize(int dataSize) {
        this.dataSize = dataSize;

        return this;
    }

    public int getDataSize() {
        return this.dataSize;
    }

    public Options setRequiredTokens(int requiredTokens) {
        this.requiredTokens = requiredTokens;

        return this;
    }

    public int getRequiredTokens() {
        return this.requiredTokens;
    }
}
