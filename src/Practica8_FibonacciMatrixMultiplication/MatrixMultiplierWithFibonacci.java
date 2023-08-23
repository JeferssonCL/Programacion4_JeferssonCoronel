package Practica8_FibonacciMatrixMultiplication;

import java.util.Arrays;
import java.util.Random;

public class MatrixMultiplierWithFibonacci {
    private long[][] matrix, fibonacciMatrix;
    private Random random;
    private int[] rangeOfValues, positions;
    private int nxn, numberCores;
    private long executionTime;
    private boolean isWithThreads;

    public MatrixMultiplierWithFibonacci(boolean isWithThreads) {
        this.isWithThreads = isWithThreads;
    }

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

    private void assignValuesToVariables(int numberCores, int nxn, int[] rangeOfValues) {
        this.nxn = nxn;
        this.random = new Random();
        this.numberCores = numberCores;
        this.matrix = new long[nxn][nxn];
        this.fibonacciMatrix = new long[nxn][nxn];
        this.rangeOfValues = rangeOfValues;
    }

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

    private void validatePositionsInNotObbValues(int currentColumn) {
        positions[0] = 0;
        positions[1] = positions[1] % nxn;
        positions[2] = currentColumn + 1;
        positions[3] = currentColumn + 1;
    }

    private void validatePositionsInObbValues(int currentColumn) {
        positions[3] = currentColumn + 1;
        positions[1] = positions[1] % nxn;
    }

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

    private void getPositions(int index, int currentRow, int currentColumn, int quantityToAdd, MyThread[] threads) {
        if (index >= threads.length) return;
        positions = new int[]{currentRow, currentRow, currentColumn, currentColumn};
        positions[1] = currentRow + quantityToAdd;

        validatePositions(quantityToAdd, index, currentColumn, threads);

        threads[index] = new MyThread(matrix, fibonacciMatrix, positions, quantityToAdd);
        threads[index].start();

        currentRow = positions[1];
        currentColumn = positions[3];
        getPositions(index + 1, currentRow, currentColumn, quantityToAdd, threads);
    }

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

    private void fillNewArrayWithFibonacciUsingThreads() throws InterruptedException {
        long startTime = System.nanoTime();
        int elementsPerThread = (nxn * nxn) / numberCores;

        MyThread[] threads = new MyThread[numberCores];
        getPositions(0, 0, 0, elementsPerThread, threads);

        for (int i = 0; i < numberCores; i++) threads[i].join();
        long endTime = System.nanoTime();
        executionTime = endTime - startTime;
    }

    //This is a recursive method
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

    public long getExecutionTime() {
        return executionTime;
    }
}
