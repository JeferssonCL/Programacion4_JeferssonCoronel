package Practica10_PracticesWithThreads1;

import java.util.concurrent.Callable;

/**
 * This class represents a Callable task that calculates the result of multiplying
 * a sequence of numbers using the SequenceTwoMultiplier singleton instance.
 * The result is calculated based on the given numberArray parameter.
 */
public class SequenceMultiplierCallable implements Callable<Integer> {
    private final int numberArray;

    /**
     * Constructs a new SequenceMultiplierCallable with the specified numberArray parameter.
     *
     * @param numberArray The numberArray for which the multiplication sequence will be calculated.
     */
    public SequenceMultiplierCallable(int numberArray) {
        this.numberArray = numberArray;
    }

    /**
     * Calculates the result of multiplying a sequence of numbers based on the numberArray
     * using the SequenceTwoMultiplier singleton instance.
     *
     * @return The result of the multiplication sequence calculation.
     */
    @Override
    public Integer call() {
        return SequenceTwoMultiplier.getInstance().doMultiplicationSequence(numberArray);
    }
}
