package Practica8_FibonacciMatrixMultiplication;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MatrixMultiplierWithFibonacci multiplier;

        multiplier = new MatrixMultiplierWithFibonacci(false);
        multiplier.run();
        long timeWithoutThreads = multiplier.getExecutionTime();

        multiplier = new MatrixMultiplierWithFibonacci(true);
        multiplier.run();
        long timeWithThreads = multiplier.getExecutionTime();

        long minExecutionTime = Math.min(timeWithoutThreads, timeWithThreads);

        System.out.println("Execution time without threads: " + timeWithoutThreads);
        System.out.println("Execution time with threads: " + timeWithThreads);
        System.out.println("Minimum execution time: " + minExecutionTime + " " +
                (minExecutionTime == timeWithoutThreads ? "(Without Threads)" : "(With Threads)"));

    }
}
