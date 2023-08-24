package Practica8_FibonacciMatrixMultiplication;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class provides input/output utility methods for the matrix multiplication program.
 */
public class InOutUtil {
    private static InOutUtil inOutUtil;

    /**
     * Get an instance of InOutUtil.
     *
     * @return An instance of InOutUtil.
     */
    public static InOutUtil getInstance() {
        if (inOutUtil == null)
            inOutUtil = new InOutUtil();
        return inOutUtil;
    }

    private int requestValuesOfRowsAndColumns() {
        int nxn;
        System.out.print("-".repeat(60) + "\n-> Please enter the number of rows and columns in the matrix (nxn): ");
        nxn = getIntegerValue();
        return nxn;
    }

    private int[] requestValuesOfRange() {
        int[] rangeOfValues = new int[2];
        System.out.print("-".repeat(60) + "\n-> Please enter the minimum range of random values: ");
        rangeOfValues[0] = getIntegerValue();
        System.out.print("-> Please enter the maximum random value range: ");
        rangeOfValues[1] = getIntegerValue();

        return rangeOfValues;
    }

    /**
     * Get the number of rows and columns for the matrix from user input.
     *
     * @return The number of rows and columns (since they're equal).
     */
    public int getRowAndColumnNumbers() {
        int nxn;
        while (true) {
            nxn = requestValuesOfRowsAndColumns();
            if (nxn != 0 & nxn < 6) break;
            else System.out.println("* Mistake! Rows and columns have to be equal, not equal to 0 and less than 6!");
        } return nxn;
    }

    /**
     * Get the number of CPU cores from user input.
     *
     * @return The number of CPU cores.
     */
    public int[] getRangeOfRandomItems() {
        int[] range;
        while (true) {
            range = requestValuesOfRange();
            if (range[0] < range[1] && range[0] != 0) break;
            else System.out.println(" * Mistake! The minor rank is greater than the major rank !");
        } return range;
    }

    /**
     * Prompt the user to enter the number of CPU cores.
     *
     * @return The number of CPU cores (defaulted to 8 if not provided, if negative, or if 0 entered).
     */
    public int getNumberOfCores() {
        Scanner sc = new Scanner(System.in);
        int cores;

        do {
            System.out.print("-".repeat(60) + "\n-> Please enter the number of cores (positive and non-zeros): ");
            try {
                cores = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {cores = Runtime.getRuntime().availableProcessors();}
        } while (cores <= 0);
        return cores;
    }

    private int getIntegerValue() {
        Scanner scanner = new Scanner(System.in);
        int numero = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                numero = scanner.nextInt();
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. You must enter a number.");
                scanner.nextLine();
            }
        } return numero;
    }

    public void getWelcomeMessage() {
        System.out.println("""

                WELCOME to the matrix multiplier using the fibonacci sequence.
                Please enter the required data:\s""");
    }

    /**
     * Print the given matrix.
     *
     * @param matrix The matrix to print.
     */
    public void printMatrix(long[][] matrix) {
        int numCols = matrix[0].length, cellWidth = 20;
        String horizontalLine = "+".concat("-".repeat((cellWidth * matrix.length) + 7)).concat("+");
        System.out.println(horizontalLine);

        for (long[] longs : matrix) {
            System.out.print("|");
            for (int j = 0; j < numCols; j++) {
                System.out.printf("%" + cellWidth + "d", longs[j]);
                System.out.print(" | ");
            } System.out.println("\n" + horizontalLine);
        }
    }

    /**
     * Print the initial and final matrices after multiplication.
     *
     * @param matrixInit   The initial matrix.
     * @param matrixFinal  The final matrix after multiplication.
     */
    public void printArrays(long[][] matrixInit, long[][] matrixFinal) {
        System.out.println("\n-> The matrix before performing the multiplication using the fibonacci sequence: \n");
        printMatrix(matrixInit);
        System.out.println("\n-> The matrix after performing the multiplication using the fibonacci sequence: \n");
        printMatrix(matrixFinal);
    }
}
