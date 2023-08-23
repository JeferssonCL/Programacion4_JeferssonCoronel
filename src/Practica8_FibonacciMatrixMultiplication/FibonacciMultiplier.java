package Practica8_FibonacciMatrixMultiplication;

public class FibonacciMultiplier {
    private static FibonacciMultiplier instance;

    public static FibonacciMultiplier getInstance() {
        if (instance == null)
            instance = new FibonacciMultiplier();
        return instance;
    }

    public long doMultiplicationFibonacci(long valueInArray) {
        long fActualValue = 1, fPrevValue = 1;
        long resultValue = 1;

        for (int i = 0; i < valueInArray; i++) {
            resultValue = resultValue * fActualValue;
            long auxValue = fActualValue;
            fActualValue = fActualValue + fPrevValue;
            fPrevValue = auxValue;
        }
        return resultValue;
    }
}
