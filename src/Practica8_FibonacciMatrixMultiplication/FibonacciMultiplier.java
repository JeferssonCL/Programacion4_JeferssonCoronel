package Practica8_FibonacciMatrixMultiplication;

/**
 * A utility class for performing multiplication using Fibonacci sequence values.
 * This class implements the Singleton pattern for creating a single instance.
 */
public class FibonacciMultiplier {

    private static FibonacciMultiplier instance;

    /**
     * Private constructor to prevent external instantiation.
     */
    private FibonacciMultiplier() {
    }

    /**
     * Retrieves the instance of FibonacciMultiplier. If an instance doesn't exist, creates one.
     *
     * @return The instance of FibonacciMultiplier.
     */
    public static FibonacciMultiplier getInstance() {
        if (instance == null)
            instance = new FibonacciMultiplier();
        return instance;
    }

    /**
     * Performs multiplication using Fibonacci sequence values up to the given number.
     *
     * @param valueInArray The number of Fibonacci values to consider for multiplication.
     * @return The result of the multiplication.
     */
    public long doMultiplicationFibonacci(long valueInArray) {
        long fActualValue = 1, fPrevValue = 1;
        long resultValue = 1;

        for (int i = 0; i < valueInArray; i++) {
            resultValue = resultValue * fActualValue;
            long auxValue = fActualValue;
            if (i != 0)
                fActualValue = fActualValue + fPrevValue;
            fPrevValue = auxValue;
        }
        return resultValue;
    }
}
