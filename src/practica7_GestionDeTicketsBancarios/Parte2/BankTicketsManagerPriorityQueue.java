package practica7_GestionDeTicketsBancarios.Parte2;

import java.util.PriorityQueue;

public class BankTicketsManagerPriorityQueue {

    private int clientNumber;
    private PriorityQueue<Ticket> queueCustomers;

    public BankTicketsManagerPriorityQueue() {
        this.clientNumber = 0;
        this.queueCustomers = new PriorityQueue<>();
    }

    public void addNewCustomer(boolean isPriority) {
        Ticket newTicket = new Ticket(clientNumber, isPriority);
        queueCustomers.add(newTicket);
        clientNumber++;
    }

    public void serveOneCustomer() {
        queueCustomers.poll();
        clientNumber--;
    }

    public void serveAllCustomer() {
        System.out.println("Starting customer service:");
        while (!queueCustomers.isEmpty()) {
            Ticket nextTicket = queueCustomers.poll();
            System.out.println("- Processing ticket: TKL-" + nextTicket);
            clientNumber --;
        }
    }

    public PriorityQueue<Ticket> getQueueCustomers() {
        return queueCustomers;
    }

    @Override
    public String toString() {
        return "BankTicketsManagerPriorityQueue{" +
                "queueCustomers=" + queueCustomers +
                '}';
    }
}
