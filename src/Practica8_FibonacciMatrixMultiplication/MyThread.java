package Practica8_FibonacciMatrixMultiplication;

/**
 * This class represents a thread for performing matrix multiplication using the Fibonacci sequence.
 */
public class MyThread extends Thread {

    private final long[][] matrix;
    private final int start;
    private final int end;

    /**
     * Constructs a MyThread object with the given matrix and range of rows to process.
     *
     * @param matrix The matrix to be processed.
     * @param start  The starting index of the rows to process.
     * @param end    The ending index of the rows to process.
     */
    public MyThread(long[][] matrix, int start, int end) {
        this.matrix = matrix;
        this.start = start;
        this.end = end;
    }

    /**
     * Executes the matrix multiplication using the Fibonacci sequence for the specified rows.
     */
    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            for (int j = 0; j < matrix[i].length; j++)
                matrix[i][j] = FibonacciMultiplier.getInstance().doMultiplicationFibonacci(matrix[i][j]);
        }
    }
}