package Utility;

import java.util.Random;

public class RandomUtils {

    private RandomUtils() {
    }

    private static long seed;
    private static Random random = new Random(seed);

    public static void setSeed(long seed) {
        RandomUtils.seed = seed;
        random.setSeed(seed);
    }

    public static Random getRandom(){
        return random;
    }

    public static void setSeedFromTime() {
        RandomUtils.seed = System.currentTimeMillis();
        random.setSeed(seed);
    }

    public static long getSeed() {
        return seed;
    }

    /**
     * Return the next random, uniformly distributed {@code double} value between {@code 0.0} (inclusive) and {@code 1.0} (exclusive).
     *
     * @return the next random, uniformly distributed {@code double} value between {@code 0.0} (inclusive) and {@code 1.0} (exclusive).
     */
    public static double nextDouble() {
        return random.nextDouble();
    }

    /**
     * Return the next random, uniformly distributed {@code int} value between
     * {@code 0} (inclusive) and {@code upperBound} (exclusive).
     *
     * @return the next random, uniformly distributed {@code int} value between
     * {@code 0} (inclusive) and {@code upperBound} (exclusive).
     */
    public static int nextInt(int upperBound) {
        return random.nextInt(upperBound);
    }

    /**
     * Returns the next random, uniformly distributed {@code int} value between
     * {@code lowerBound} (inclusive) and {@code upperBound} (exclusive).
     *
     * @return the next random, uniformly distributed {@code int} value between
     * {@code lowerBound} (inclusive) and {@code upperBound} (exclusive).
     */
    public static int nextInt(int lowerBound, int upperBound) {
        return lowerBound + random.nextInt(upperBound - lowerBound);
    }
}
