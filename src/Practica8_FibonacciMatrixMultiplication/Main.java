package Practica8_FibonacciMatrixMultiplication;

import java.awt.*;

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
        long maxExecutionTime = Math.max(timeWithoutThreads, timeWithThreads);

        System.out.println("Execution time without threads: " + timeWithoutThreads + " ns");
        System.out.println("Execution time with threads: " + timeWithThreads + " ns");
        System.out.println(" -> Minimum execution time: " + minExecutionTime + " " +
                (minExecutionTime == timeWithoutThreads ? "(Without Threads)" : "(With Threads)") + " ns.");
        System.out.println(" -> Difference between two times: " + (maxExecutionTime - minExecutionTime) + " ns.");
    }
}
