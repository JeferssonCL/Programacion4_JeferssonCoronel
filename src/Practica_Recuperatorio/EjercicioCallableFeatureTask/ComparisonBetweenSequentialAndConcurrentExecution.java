package Practica_Recuperatorio.EjercicioCallableFeatureTask;

import Practica_Recuperatorio.EjercicioDeCodility.SolutionOfAAndB;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ComparisonBetweenSequentialAndConcurrentExecution {
    public static void main(String[] args) {
        SolutionOfAAndB solution = new SolutionOfAAndB();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the number of rows: ");
        int n = scanner.nextInt();

        int[][] matrix = new int[n][2];
        int count = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                matrix[i][j] = count;
                count++;
            }
        }

        long startTimeSeq = System.nanoTime();
        System.out.println("\nSequential Execution:");
        sequentialExecution(solution, matrix, n);
        long endTimeSeq = System.nanoTime();
        long executionTimeSeq = endTimeSeq - startTimeSeq;
        System.out.println("Sequential time: " + executionTimeSeq + " ns");

        ExecutorService executor = Executors.newFixedThreadPool(n);
        long startTimeConcurrent = System.nanoTime();
        System.out.println("\nConcurrent Execution:");
        concurrentExecution(solution, matrix, n, executor);
        long endTimeConcurrent = System.nanoTime();
        long executionTimeConcurrent = endTimeConcurrent - startTimeConcurrent;
        System.out.println("Concurrent time: " + executionTimeConcurrent + " ns");
        executor.shutdown();

        System.out.println("-".repeat(60));
        if (executionTimeConcurrent > executionTimeSeq) {
            System.out.println("- Minimum execution time (Execution Time Sequential): " + executionTimeSeq);
            System.out.println("- Difference between two times: " + (executionTimeConcurrent - executionTimeSeq));
        } else {
            System.out.println("- Minimum execution time (Execution Time Concurrent): " + executionTimeConcurrent);
            System.out.println("- Difference between two times: " + (executionTimeSeq - executionTimeConcurrent));
        }
    }

    private static void sequentialExecution(SolutionOfAAndB solution, int[][] matrix, int n) {
        for (int i = 0; i < n; i++) {
            int A = matrix[i][0];
            int B = matrix[i][1];
            String result = solution.solution(A, B);
            System.out.println("Row " + i + ": " + result);
        }
    }

    private static void concurrentExecution(SolutionOfAAndB solution, int[][] matrix, int n, ExecutorService executor) {
        FutureTask<String>[] tasks = new FutureTask[n];

        for (int i = 0; i < n; i++) {
            int A = matrix[i][0];
            int B = matrix[i][1];
            Callable<String> callable = () -> solution.solution(A, B);
            tasks[i] = new FutureTask<>(callable);
            executor.submit(tasks[i]);
        }

        try {
            for (int i = 0; i < n; i++) {
                String result = tasks[i].get();
                System.out.println("Row " + i + ": " + result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
