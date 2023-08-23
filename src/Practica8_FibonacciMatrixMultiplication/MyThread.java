package Practica8_FibonacciMatrixMultiplication;

import java.util.Arrays;

/**
 * This class represents a thread for matrix multiplication with the Fibonacci sequence.
 */
public class MyThread extends Thread {

    private final long[][] matrix;
    private final long[][] matrixToMultiply;
    private final int columnStart;
    private final int columnEnd;
    private final int rowStart;
    private final int quantityToAd;
    private final int rowEnd;

    /**
     * Constructor for MyThread.
     *
     * @param matrix           Source matrix for multiplication.
     * @param matrixToMultiply Target matrix for storing results.
     * @param positions        Positions for thread's work within the matrices.
     * @param quantityToAd     Quantity used for conditional logic.
     */
    public MyThread(long[][] matrix, long[][] matrixToMultiply, int[] positions, int quantityToAd) {
        System.out.println(Arrays.toString(positions));
        this.matrix = matrix;
        this.matrixToMultiply = matrixToMultiply;
        this.columnStart = positions[2];
        this.columnEnd = positions[3];
        this.rowStart = positions[0];
        this.rowEnd = positions[1];
        this.quantityToAd = quantityToAd;
    }

    /**
     * Executes matrix multiplication with the Fibonacci sequence within the specified range.
     */
    public void run() {
        for (int i = columnStart; i <= columnEnd; i++) {
            for (int j = rowStart; j <= rowEnd; j++) {
                if (quantityToAd % 2 == 0) {
                    if (j == rowEnd)
                        matrixToMultiply[i][j - 1] = FibonacciMultiplier.getInstance().doMultiplicationFibonacci(matrix[i][j - 1]);
                    else
                        matrixToMultiply[i][j] = FibonacciMultiplier.getInstance().doMultiplicationFibonacci(matrix[i][j]);
                } else
                    matrixToMultiply[i][j] = FibonacciMultiplier.getInstance().doMultiplicationFibonacci(matrix[i][j]);
            }
        }
    }

    /**
     * Get the starting column index for this thread's operation.
     *
     * @return Starting column index.
     */
    public int getColumnStart() {
        return columnStart;
    }

    /**
     * Get the ending column index for this thread's operation.
     *
     * @return Ending column index.
     */
    public int getColumnEnd() {
        return columnEnd;
    }

    /**
     * Get the starting row index for this thread's operation.
     *
     * @return Starting row index.
     */
    public int getRowStart() {
        return rowStart;
    }

    /**
     * Get the ending row index for this thread's operation.
     *
     * @return Ending row index.
     */
    public int getRowEnd() {
        return rowEnd;
    }
}
