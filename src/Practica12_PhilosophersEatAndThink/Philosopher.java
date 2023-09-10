package Practica12_PhilosophersEatAndThink;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;

/**
 * A Philosopher represents a philosopher who thinks, eats, and puts down forks.
 */
public class Philosopher implements Callable<String> {
    private final int id;
    private final Lock leftFork;
    private final Lock rightFork;

    /**
     * Creates a new Philosopher with the given ID and forks.
     *
     * @param id         The ID of the philosopher.
     * @param leftFork   The left fork the philosopher can use.
     * @param rightFork  The right fork the philosopher can use.
     */
    public Philosopher(int id, Lock leftFork, Lock rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public String call() {
        while (true) {
            think();
            takeForks();
            eat();
            putForks();
        }
    }

    /**
     * Simulates the philosopher thinking.
     */
    private void think() {
        System.out.println("Philosopher " + id + " is thinking.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Simulates the philosopher taking both forks.
     */
    private void takeForks() {
        Lock firstFork = leftFork.hashCode() < rightFork.hashCode() ? leftFork : rightFork;
        Lock secondFork = leftFork.hashCode() < rightFork.hashCode() ? rightFork : leftFork;

        firstFork.lock();
        System.out.println("Philosopher " + id + " has taken the first fork.");
        secondFork.lock();
        System.out.println("Philosopher " + id + " has taken the second fork.");
    }

    /**
     * Simulates the philosopher eating.
     */
    private void eat() {
        System.out.println("Philosopher " + id + " is eating.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Simulates the philosopher putting down both forks.
     */
    private void putForks() {
        leftFork.unlock();
        System.out.println("Philosopher " + id + " has put down the left fork.");
        rightFork.unlock();
        System.out.println("Philosopher " + id + " has put down the right fork.");
    }
}
