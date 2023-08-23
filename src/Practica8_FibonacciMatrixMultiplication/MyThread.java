package Practica8_FibonacciMatrixMultiplication;

import java.util.Arrays;

public class MyThread extends Thread{

    private final long[][] matrix;
    private final long[][] matrixToMultiply;
    private final int columnStart;
    private final int columnEnd;
    private final int rowStart;
    private final int rowEnd;

    public MyThread(long[][] matrix, long[][] matrixToMultiply, int[] positions) {
        System.out.println(Arrays.toString(positions));
        this.matrix = matrix;
        this.matrixToMultiply = matrixToMultiply;
        this.columnStart = positions[2];
        this.columnEnd = positions[3];
        this.rowStart = positions[0];
        this.rowEnd = positions[1];
    }

    public void run() {
        for (int i = columnStart; i <= columnEnd; i++) {
            for (int j = rowStart; j <= rowEnd; j++) {
                matrixToMultiply[i][j] =
                        FibonacciMultiplier.getInstance().doMultiplicationFibonacci(matrix[i][j]);}
        }
    }

    public int getColumnStart() {
        return columnStart;
    }

    public int getColumnEnd() {
        return columnEnd;
    }

    public int getRowStart() {
        return rowStart;
    }

    public int getRowEnd() {
        return rowEnd;
    }
}
