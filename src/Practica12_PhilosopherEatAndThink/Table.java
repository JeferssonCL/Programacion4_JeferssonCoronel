package Practica12_PhilosopherEatAndThink;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Table class represents the dining table where philosophers eat and think.
 */
public class Table {

    /**
     * The main method creates a dining table with philosophers and forks and starts their activities.
     *
     * @param args Command-line arguments (not used in this application).
     * @throws InterruptedException If a thread is interrupted while waiting.
     * @throws ExecutionException   If a computation has a problem.
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int numPhilosophers = 5;
        Lock[] forks = new Lock[numPhilosophers];

        for (int i = 0; i < numPhilosophers; i++)
            forks[i] = new ReentrantLock();

        FutureTask[] philosophers = new FutureTask[numPhilosophers];

        for (int i = 0; i < numPhilosophers; i++) {
            philosophers[i] = new FutureTask<>(new Philosopher(i, forks[i], forks[(i + 1) % numPhilosophers]));
            Thread thread = new Thread(philosophers[i]);
            thread.start();
        }

        for (int i = 0; i < numPhilosophers; i++)
            System.out.println(philosophers[i].get());
    }
}

