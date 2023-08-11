package practica7_GestionDeTicketsBancarios;

import practica7_GestionDeTicketsBancarios.Parte1.BankTicketsManager;
import practica7_GestionDeTicketsBancarios.Parte2.BankTicketsManagerPriorityQueue;
import practica7_GestionDeTicketsBancarios.Parte2.Main;

public class HeapVsPriorityQueueComparison {

    public void compareMethodAdd() {
        long startTime;
        long endTime;

        // Custom Heap
        BankTicketsManager bankTicketsManager = new BankTicketsManager();

        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++)
            bankTicketsManager.addNewCustomerToQueue(true);

        endTime = System.nanoTime();
        System.out.println("Custom Heap - Time taken for insertion: " + (endTime - startTime) + " ns");

        // Java Priority Queue
        BankTicketsManagerPriorityQueue bankTicketsManagerPriorityQueue = new BankTicketsManagerPriorityQueue();

        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++)
            bankTicketsManager.addNewCustomerToQueue(true);

        endTime = System.nanoTime();
        System.out.println("Java Priority Queue - Time taken for insertion: " + (endTime - startTime) + " ns");
    }

    public void compareMethodRemoveOrPoll() throws InterruptedException {
        long startTime;
        long endTime;

        // Custom Heap
        BankTicketsManager bankTicketsManager = new BankTicketsManager();

        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++)
            bankTicketsManager.serveAllCustomer(false);

        endTime = System.nanoTime();
        System.out.println("Custom Heap - Time taken for removal: " + (endTime - startTime) + " ns");

        // Java Priority Queue
        BankTicketsManagerPriorityQueue bankTicketsManagerPriorityQueue = new BankTicketsManagerPriorityQueue();

        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++)
            bankTicketsManager.serveAllCustomer(false);

        endTime = System.nanoTime();
        System.out.println("Java Priority Queue - Time taken for removal: " + (endTime - startTime) + " ns");
    }

    public static void main(String[] args) throws InterruptedException {
        HeapVsPriorityQueueComparison comparison = new HeapVsPriorityQueueComparison();

        System.out.println("METHOD ADD COMPARISON");
        comparison.compareMethodAdd();
        System.out.println("\nMETHOD REMOVE COMPARISON");
        comparison.compareMethodRemoveOrPoll();
    }
}
