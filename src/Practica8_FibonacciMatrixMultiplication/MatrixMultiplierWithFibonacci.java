package Practica8_FibonacciMatrixMultiplication;

import java.util.Random;

/**
 * This class performs matrix multiplication using the Fibonacci sequence.
 */
public class MatrixMultiplierWithFibonacci {
    private long[][] matrix, fibonacciMatrix;
    private Random random;
    private int[] rangeOfValues, positions;
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
        if (isWithThreads)
            fillNewArrayWithFibonacciUsingThreads();
        else
            fillNewArrayWithFibonacci();
        InOutUtil.getInstance().printArrays(matrix, fibonacciMatrix);
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
        this.positions = new int[4];
        this.fibonacciMatrix = new long[nxn][nxn];
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
     * Validates positions for even quantity addition.
     *
     * @param currentColumn The current column index.
     */
    private void validatePositionsInNotObbValues(int currentColumn) {
        positions[0] = 0;
        positions[1] = positions[1] % nxn;
        positions[2] = currentColumn + 1;
        positions[3] = currentColumn + 1;
    }

    /**
     * Validates positions for odd quantity addition.
     *
     * @param currentColumn The current column index.
     */
    private void validatePositionsInObbValues(int currentColumn) {
        positions[3] = currentColumn + 1;
        positions[1] = positions[1] % nxn;
    }

    /**
     * Validates positions and updates thread positions.
     *
     * @param quantityToAdd The quantity to add.
     * @param index         The current thread index.
     * @param currentColumn The current column index.
     * @param threads       The array of threads.
     */
    private void validatePositions(int quantityToAdd, int index, int currentColumn, MyThread[] threads) {
        if (quantityToAdd % 2 != 0) {
            if (positions[1] >= nxn) {
                if (index != threads.length - 1) validatePositionsInObbValues(currentColumn);
                else positions[1] = nxn - 1;
            }
        } else {
            if (positions[1] > nxn)
                if (index != threads.length - 1)
                    validatePositionsInNotObbValues(currentColumn);
                else positions[1] = positions[1]++;
        }
    }

    /**
     * Sets thread positions and starts matrix multiplication threads.
     *
     * @param threads       The array of threads.
     * @param quantityToAdd The quantity to add.
     */
    private void getPositions(MyThread[] threads, int quantityToAdd) {
        for (int index = 0; index < threads.length; index++) {
            int currentRow = positions[1];
            int currentColumn = positions[3];
            positions[1] = currentRow + quantityToAdd;

            validatePositions(quantityToAdd, index, currentColumn, threads);

            threads[index] = new MyThread(matrix, fibonacciMatrix, positions, quantityToAdd);
            threads[index].start();
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
                fibonacciMatrix[indexColumn][indexRow] = valueToAdd;
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
        int elementsPerThread = (nxn * nxn) / numberCores;

        MyThread[] threads = new MyThread[numberCores];
        getPositions(threads, elementsPerThread);

        long startTime = System.nanoTime();
        for (int i = 0; i < numberCores; i++) threads[i].join();
        long endTime = System.nanoTime();
        executionTime = endTime - startTime;
    }

    //This is a recursive method
    /**
     * Fills the Fibonacci matrix using recursion.
     *
     * @param indexColumn The current column index.
     * @param indexRow    The current row index.
     */
    private void fillNewArrayWithFibonacci(int indexColumn, int indexRow) {
        if (indexColumn < nxn && indexRow < nxn) {
            long valueToAdd = FibonacciMultiplier.getInstance().doMultiplicationFibonacci(matrix[indexColumn][indexRow]);
            fibonacciMatrix[indexColumn][indexRow] = valueToAdd;
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
