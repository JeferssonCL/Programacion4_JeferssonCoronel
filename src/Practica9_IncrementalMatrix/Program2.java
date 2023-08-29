package Practica9_IncrementalMatrix;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Program2 {
    private long executionTime;
    private int[][] matrix;

    public void run() throws InterruptedException {
        int n = 20000;
        matrix = new int[n][n];
        ExecutorService executorService = Executors.newFixedThreadPool(n);

        long startTime = System.nanoTime();

        for (int i = 0; i < n; i++) {
            final int row = i;
            executorService.execute(() -> {
                for (int j = 0; j < n; j++)
                    matrix[row][j] = (row * n) + j + 1;
            });
        }

        executorService.shutdown();
        while (!executorService.isTerminated()) { }

        long endTime = System.nanoTime();
        System.out.println(Arrays.toString(matrix));
        executionTime = endTime - startTime;
    }

    public long getExecutionTime() {
        return executionTime;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int[] row : matrix) {
            sb.append(Arrays.toString(row)).append(" ");
            sb.append("\n");
        } return sb.toString();
    }
}
