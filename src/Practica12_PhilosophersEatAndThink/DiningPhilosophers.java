package Practica12_PhilosophersEatAndThink;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {
    private static final int NUM_PHILOSOPHERS = 5;
    private static Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];
    private static Object[] forks = new Object[NUM_PHILOSOPHERS];
    private static Lock[] locks = new Lock[NUM_PHILOSOPHERS];

    public static void main(String[] args) {
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new Object();
            locks[i] = new ReentrantLock();
        }

        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            philosophers[i] = new Philosopher(i);
        }

        for (Philosopher philosopher : philosophers) {
            philosopher.start();
        }
    }

    static class Philosopher extends Thread {
        private int id;

        public Philosopher(int id) {
            this.id = id;
        }

        private void think() {
            System.out.println("Philosopher " + id + " is thinking.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void eat() {
            System.out.println("Philosopher " + id + " is eating.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (true) {
                think();
                int leftFork = id;
                int rightFork = (id + 1) % NUM_PHILOSOPHERS;

                // Use synchronized for the left fork
                synchronized (forks[leftFork]) {
                    System.out.println("Philosopher " + id + " picked up left fork.");
                    // Use ReentrantLock for the right fork
                    locks[rightFork].lock();
                    try {
                        System.out.println("Philosopher " + id + " picked up right fork.");
                        eat();
                    } finally {
                        locks[rightFork].unlock();
                        System.out.println("Philosopher " + id + " put down right fork.");
                    }
                }
                System.out.println("Philosopher " + id + " put down left fork.");
            }
        }
    }
}
