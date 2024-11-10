package math;

public class Functions {

    public static long fibonacci(int n) {
        return switch(n) {
            case 0 ->  0l;
            case 1 ->  1l;
            default -> { yield fibonacci(n - 1) + fibonacci(n - 2); }
        };
    }

    public static boolean isPrim(int n) {
        if (n < 2) {
            return false;
        }
    
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
     * @param n
     * @param guess Initial guess. IF you don't know, use 1.0d.
     * @param steps The number of steps to calculate the result. 
     *              The larger the number for which you want to compute the 
     *              cubic root, the more steps you need.
     * @return
     */
    private static double cbrt(final double n, double guess, int steps) {
        if (steps == 0)
            return guess;
    
        guess = guess - ((guess * guess * guess - n) / (3 * guess * guess));
        return cbrt(n, guess, steps - 1);
    }

    /**
     * Computes an approximation of the cubic root of a number n.
     */
    public static double cbrt(final double n) {
        return cbrt(n, 1.0d, 100);
    }
}
