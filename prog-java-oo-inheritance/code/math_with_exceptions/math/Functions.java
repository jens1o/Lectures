package math;

public interface Functions {

    static final int THE_ANSWER_TO_THE_ULTIMATE_QUESTION_OF_LIFE_THE_UNIVERSE_AND_EVERYTHING = 42;

    static long fibonacci(int n) throws IllegalArgumentException  {
        if (n < 0) 
            throw new IllegalArgumentException("n must be greater or equal to 0");
        
        return switch (n) {
            case 0 -> 0l;
            case 1 -> 1l;
            default -> {
                yield fibonacci(n - 1) + fibonacci(n - 2);
            }
        };
    }

    static boolean isPrim(int n) throws IllegalArgumentException  {
        if (n < 0) 
            throw new IllegalArgumentException("n must be greater or equal to 0");

        if (n < 2)             return false;
        boolean isPrim = true;
        for (int i = 2; i < n / 2; i++) {
            if (n % i == 0) {
                isPrim = false;
                break;
            }
        }
        return isPrim;
    }

    /**
     * Computes an approximation of the cubic root of a number n.
     * 
     * @param n     The number for which you want to compute the cubic root.
     * @param guess Initial guess of the cubic root. IF you don't know, use 1.0d.
     * @param steps The number of steps to calculate the result.
     *              The larger the number for which you want to compute the cubic
     *              root is, the more steps you need.
     * @return an approximation of the cubic root of n.
     */
    static double cbrt(
            final double n, double guess, int steps
    ) throws IllegalArgumentException {
        if (steps < 1) 
            throw new IllegalArgumentException("steps must be greater than 0");
        if (Double.isNaN(n) || Double.isInfinite(n)) 
            throw new IllegalArgumentException("n must be a finite number");
        if (Double.isNaN(guess) || Double.isInfinite(guess)) 
            throw new IllegalArgumentException("n must be a finite number");

        if (steps == 0) return guess;

        guess = guess - ((guess * guess * guess - n) / (3 * guess * guess));
        return cbrt(n, guess, steps - 1);
    }

    static double cbrt(final double n) {
        return cbrt(n, 1.0d, 100);
    }
}
