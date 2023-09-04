package Practica10_PracticesWithThreads1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * This class represents an application that performs matrix calculations using threads.
 * It reads user input to determine the number of elements in the matrix, fills the matrix
 * with initial values, performs calculations using multiple threads, and prints the results.
 */
public class App {
    int n;
    int[] matrix;

    /**
     * Fills the matrix with initial values (1, 2, 3, ...).
     */
    private void fillMatrix() {
        matrix = new int[n];

        for (int i = 0; i < n; i++) {
            matrix[i] = i + 1;
        }
    }

    /**
     * Reads user input to determine the number of elements in the matrix.
     */
    private void readInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print(" - Please enter the quantity of elements of array: ");
        this.n = sc.nextInt();
        this.matrix = new int[n];
        sc.close();
    }

    /**
     * Performs matrix calculations using multiple threads and stores the results in the matrix.
     *
     * @throws InterruptedException if any thread is interrupted while waiting.
     * @throws ExecutionException   if any computation threw an exception.
     */
    private void calculateMatrixValues() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(n);
        ArrayList<Future<Integer>> results = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            Callable<Integer> contadorCallable = new SequenceMultiplierCallable(i + 1);
            Future<Integer> future = executor.submit(contadorCallable);
            results.add(future);
        }

        executor.shutdown();

        for (int i = 0; i < n; i++) {
            Future<Integer> res = results.get(i);
            matrix[i] = res.get();
        }
    }

    /**
     * Prints the matrix along with a specified name.
     *
     * @param matrixName The name to display along with the matrix.
     */
    private void printMatrix(String matrixName) {
        System.out.println(matrixName + Arrays.toString(matrix));
    }

    /**
     * Runs the application, including reading input, filling the matrix,
     * performing calculations, and printing the results.
     *
     * @throws InterruptedException if any thread is interrupted while waiting.
     * @throws ExecutionException   if any computation threw an exception.
     */
    public void run() throws ExecutionException, InterruptedException {
        readInput();
        fillMatrix();
        printMatrix("\t\t* Array before multiplying the sequence on each element: ");
        calculateMatrixValues();
        printMatrix("\t\t* Array after multiplying the sequence on each element: ");
    }

    /**
     * The main entry point of the application.
     *
     * @param args The command-line arguments (not used).
     * @throws InterruptedException if any thread is interrupted while waiting.
     * @throws ExecutionException   if any computation threw an exception.
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        App app = new App();
        app.run();
    }
}
