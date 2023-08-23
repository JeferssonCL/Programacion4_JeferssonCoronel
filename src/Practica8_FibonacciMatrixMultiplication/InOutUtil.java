package Practica8_FibonacciMatrixMultiplication;

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

    private int[] requestValuesOfRowsAndColumns(Scanner sc) {
        int[] nxn = new int[2];
        System.out.print("-".repeat(60) + "\n-> Please enter the number of rows in the matrix: ");
        nxn[0] = sc.nextInt();
        System.out.print("-> Please enter the number of columns in the matrix: ");
        nxn[1] = sc.nextInt();

        return nxn;
    }

    private int[] requestValuesOfRange(Scanner sc) {
        int[] rangeOfValues = new int[2];
        System.out.print("-".repeat(60) + "\n-> Please enter the minimum range of random values: ");
        rangeOfValues[0] = sc.nextInt();
        System.out.print("-> Please enter the maximum random value range: ");
        rangeOfValues[1] = sc.nextInt();

        return rangeOfValues;
    }

    /**
     * Get the number of rows and columns for the matrix from user input.
     *
     * @return The number of rows and columns (since they're equal).
     */
    public int getRowAndColumnNumbers() {
        Scanner sc = new Scanner(System.in);
        int[] nxn;
        while (true) {
            nxn = requestValuesOfRowsAndColumns(sc);
            if (nxn[0] == nxn[1] && nxn[1] != 0 & nxn[0] < 7) break;
            else System.out.println("* Mistake! Rows and columns have to be equal, not equal to 0 and less than 7!");
        } return nxn[1];
    }

    /**
     * Get the number of CPU cores from user input.
     *
     * @return The number of CPU cores.
     */
    public int[] getRangeOfRandomItems() {
        Scanner sc = new Scanner(System.in);
        int[] range;
        while (true) {
            range = requestValuesOfRange(sc);
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
