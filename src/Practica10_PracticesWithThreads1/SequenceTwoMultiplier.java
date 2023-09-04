package Practica10_PracticesWithThreads1;

/**
 * This class represents a singleton utility for calculating the number of times a given
 * integer can be divided by 2 (while remaining even), effectively counting the number of
 * trailing zeros in its binary representation.
 */
public class SequenceTwoMultiplier {
    private static SequenceTwoMultiplier instance;

    /**
     * Private constructor to enforce the singleton pattern.
     */
    private SequenceTwoMultiplier() {
    }

    /**
     * Retrieves the singleton instance of the SequenceTwoMultiplier.
     *
     * @return The singleton instance of the SequenceTwoMultiplier.
     */
    public static SequenceTwoMultiplier getInstance() {
        if (instance == null)
            instance = new SequenceTwoMultiplier();
        return instance;
    }

    /**
     * Calculates the number of times the given integer can be divided by 2 (while remaining even),
     * effectively counting the number of trailing zeros in its binary representation.
     *
     * @param n The integer for which the trailing zeros count is calculated.
     * @return The count of trailing zeros in the binary representation of the integer.
     */
    public int doMultiplicationSequence(int n) {
        int result = 0;
        while (n % 2 == 0) {
            n = n / 2;
            result++;
        }
        return result;
    }
}
