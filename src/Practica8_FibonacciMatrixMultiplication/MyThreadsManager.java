package Practica8_FibonacciMatrixMultiplication;

import java.util.ArrayList;
import java.util.List;

/**
 * This class manages and coordinates multiple threads for performing matrix multiplication with the Fibonacci sequence.
 */
public class MyThreadsManager {

    private final long[][] matrix;
    private final int numberThreads;
    private final List<Thread> threads;

    /**
     * Constructs a MyThreadsManager object with the given matrix and number of threads.
     *
     * @param matrix The matrix to be processed.
     * @param numberThreads The number of threads to be used for processing.
     */
    public MyThreadsManager(long[][] matrix, int numberThreads) {
        this.matrix = matrix;
        this.numberThreads = numberThreads;
        this.threads = new ArrayList<>();
    }

    /**
     * Distributes the work across threads and starts their execution.
     *
     * @throws InterruptedException If any of the threads is interrupted during execution.
     */
    public long start() throws InterruptedException {
        distributeWork();

        for (Thread thread : threads)
            thread.start();

        long startTime = System.nanoTime();

        for (Thread thread : threads)
            thread.join();

        long endTime = System.nanoTime();
        return (endTime - startTime);
    }

    /**
     * Distributes the matrix rows among the available threads.
     */
    public void distributeWork() {
        int rowsPerThread = matrix.length / numberThreads;
        int remainderRows = matrix.length % numberThreads;

        for (int i = 0; i < numberThreads; i++) {
            int startRow = i * rowsPerThread;
            int endRow = startRow + rowsPerThread;

            if (i == numberThreads - 1) {
                endRow += remainderRows;
            }

            MyThread thread = new MyThread(matrix, startRow, endRow);
            threads.add(thread);
        }
    }
}