package Practica9_IncrementalMatrix;

import java.util.Arrays;

public class Program1 {

    private long executionTime;
    private int[][] matrix;

    public void run() throws InterruptedException {
        int n = 20000;
        matrix = new int[n][n];
        Thread[] threads = new Thread[n];

        long startTime = System.nanoTime();

        for (int i = 0; i < n; i++) {
            final int row = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < n; j++)
                    matrix[row][j] = (row * n) + j + 1;
            }); threads[i].start();
        }

        for (Thread thread : threads)
            thread.join();

        long endTime = System.nanoTime();
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
