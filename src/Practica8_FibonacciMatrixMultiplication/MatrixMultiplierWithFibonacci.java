package Practica8_FibonacciMatrixMultiplication;

import java.util.Random;

/**
 * This class performs matrix multiplication using the Fibonacci sequence.
 */
public class MatrixMultiplierWithFibonacci {
    private long[][] matrix;
    private Random random;
    private int[] rangeOfValues;
    private int nxn, numberCores;
    private long executionTime;
    private final boolean isWithThreads;

    /**
     * Constructor for MatrixMultiplierWithFibonacci.
     *
     * @param isWithThreads Whether to use threads for multiplication.
     */
    public MatrixMultiplierWithFibonacci(boolean isWithThreads) {
        this.isWithThreads = isWithThreads;
    }

    /**
     * Runs the matrix multiplication with the Fibonacci sequence.
     *
     * @throws InterruptedException if interrupted during thread execution.
     */
    public void run() throws InterruptedException {
        InOutUtil.getInstance().getWelcomeMessage();

        int nxn = InOutUtil.getInstance().getRowAndColumnNumbers();
        int numberCores = InOutUtil.getInstance().getNumberOfCores();
        int[] rangeOfValues = InOutUtil.getInstance().getRangeOfRandomItems();

        assignValuesToVariables(numberCores, nxn, rangeOfValues);
        fillMatrixWithValuesInRange(0, 0);
        InOutUtil.getInstance().printArray(matrix, "before");

        validateMultiplicationFibonacci();

        InOutUtil.getInstance().printArray(matrix, "after");
    }

    private void validateMultiplicationFibonacci() throws InterruptedException {
        if (isWithThreads)
            fillNewArrayWithFibonacciUsingThreads();
        else
            fillNewArrayWithFibonacci();
    }

    /**
     * Assigns values to class variables for further use.
     *
     * @param numberCores    The number of CPU cores to be used.
     * @param nxn            The number of rows/columns in the matrix.
     * @param rangeOfValues  The range of random values for matrix elements.
     */
    private void assignValuesToVariables(int numberCores, int nxn, int[] rangeOfValues) {
        this.nxn = nxn;
        this.random = new Random();
        this.numberCores = numberCores;
        this.matrix = new long[nxn][nxn];
        this.rangeOfValues = rangeOfValues;
    }

    /**
     * Populates the matrix with values within a specified range.
     *
     * @param indexColumn The current column index.
     * @param indexRow    The current row index.
     */
    public void fillMatrixWithValuesInRange(int indexColumn, int indexRow) {
        if (indexColumn < nxn && indexRow < nxn) {
            int valueToAdd = random.nextInt(rangeOfValues[1] - rangeOfValues[0] + 1) + rangeOfValues[0];
            matrix[indexColumn][indexRow] = valueToAdd;
            indexRow++;
            fillMatrixWithValuesInRange(indexColumn, indexRow);
        } else if (indexColumn < nxn && indexRow == nxn) {
            indexColumn++;
            indexRow = 0;
            fillMatrixWithValuesInRange(indexColumn, indexRow);
        }
    }

    /**
     * Fills the Fibonacci matrix without using threads.
     */
    private void fillNewArrayWithFibonacci() {
        long startTime = System.nanoTime();
        for (int indexColumn = 0; indexColumn < nxn; indexColumn++) {
            for (int indexRow = 0; indexRow < nxn; indexRow++) {
                long valueToAdd = FibonacciMultiplier.getInstance().doMultiplicationFibonacci(matrix[indexColumn][indexRow]);
                matrix[indexColumn][indexRow] = valueToAdd;
            }
        }
        long endTime = System.nanoTime();
        executionTime = endTime - startTime;

    }

    /**
     * Fills the Fibonacci matrix using threads.
     *
     * @throws InterruptedException if interrupted during thread execution.
     */
    private void fillNewArrayWithFibonacciUsingThreads() throws InterruptedException {
       MyThreadsManager myThreadsManager = new MyThreadsManager(matrix, numberCores);
       executionTime = myThreadsManager.start();;
    }

    /**
     * Fills the Fibonacci matrix using recursion.
     *
     * @param indexColumn The current column index.
     * @param indexRow    The current row index.
     */
    private void fillNewArrayWithFibonacci(int indexColumn, int indexRow) {
        if (indexColumn < nxn && indexRow < nxn) {
            long valueToAdd = FibonacciMultiplier.getInstance().doMultiplicationFibonacci(matrix[indexColumn][indexRow]);
            matrix[indexColumn][indexRow] = valueToAdd;
            indexRow++;
            fillNewArrayWithFibonacci(indexColumn, indexRow);
        } else if (indexColumn < nxn && indexRow == nxn) {
            indexColumn++;
            indexRow = 0;
            fillNewArrayWithFibonacci(indexColumn, indexRow);
        }
    }

    /**
     * Gets the execution time of the matrix multiplication.
     *
     * @return The execution time.
     */
    public long getExecutionTime() {
        return executionTime;
    }
}
