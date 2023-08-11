package practica7_GestionDeTicketsBancarios.Parte2;

public class Main {

    public static void main(String[] args) {
        BankTicketsManagerPriorityQueue bncBank =new BankTicketsManagerPriorityQueue();

        // Adding tickets to the queue
        bncBank.addNewCustomer(false); // Regular ticket
        bncBank.addNewCustomer(false); // Regular ticket
        bncBank.addNewCustomer(false); // Regular ticket
        bncBank.addNewCustomer(false); // Regular ticket
        bncBank.addNewCustomer(true); // Priority ticket
        bncBank.addNewCustomer(true); // Regular ticket

        // Processing tickets based on priority and ID
        bncBank.serveAllCustomer();
    }
}
