package practica7_GestionDeTicketsBancarios.Parte1;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * The BankTicketsManager class manages a queue of tickets for a bank.
 * It uses a heap-based priority queue to prioritize tickets from priority customers.
 */
public class BankTicketsManager {

    private final Heap<Ticket> root;
    private int numberCustomer;

    /**
     * Constructs a new BankTicketsManager instance with an empty heap.
     */
    public BankTicketsManager() {
        root = new Heap<>(true);
    }

    /**
     * Adds a new customer to the queue with the given priority status.
     *
     * @param isPriorityCustomer Indicates whether the customer is a priority customer.
     */
    public void addNewCustomerToQueue(boolean isPriorityCustomer) {
        numberCustomer++;

        String code = generateTicket(isPriorityCustomer);
        Ticket newTicket = new Ticket(code, isPriorityCustomer, numberCustomer);

        if (isPriorityCustomer && root.size() > 0) {
            int lastPriorityIndex = findLastPriorityCustomer();
            Ticket previousTicket = root.removeByIndex(lastPriorityIndex);
            previousTicket.setNumberInQueue(lastPriorityIndex + 1);
            newTicket.setNumberInQueue(lastPriorityIndex);
            root.insert(newTicket);
            root.insert(previousTicket);
        } else root.insert(newTicket);
    }

    /**
     * Serves one customer by processing and removing the highest-priority ticket from the queue.
     *
     * @return A message indicating the processing of the served customer's ticket.
     * @throws IllegalStateException If the heap is empty.
     */
    public String serveOneCustomer() {
        if (root.size() == 0) return "No tickets in the queue.";

        Ticket ticket = root.poll();
        String currentTicket = ticket.getCode();
        String nextTicket = root.size() == 0 ? "No more tickets" : root.peek().getCode();
        String bankName = "BCN Bank";

        return ManagerString.getInstance().generateSimulationAttentionTicket(currentTicket, nextTicket, bankName);
    }

    /**
     * Serves all customers by processing and removing their tickets from the queue.
     *
     * @return A string containing messages for each processed customer's ticket.
     */
    public String serveAllCustomer(boolean isTest) throws InterruptedException {
        StringBuilder sb = new StringBuilder();

        if (isTest) ManagerString.getInstance().generateCustomerServiceAttention();

        while (root.size() != 0) {
            String ticketOneCustomer = serveOneCustomer() + "\n";
            sb.append(ticketOneCustomer);
            if (isTest) {
                System.out.println(ticketOneCustomer);
                TimeUnit.SECONDS.sleep(2);
            }
        } return sb.toString();
    }

    /**
     * Generates a new ticket code based on the customer's priority status.
     *
     * @param isPriorityCustomer Indicates whether the customer is a priority customer.
     * @return The generated ticket code.
     */
    private String generateTicket(boolean isPriorityCustomer) {
        StringBuilder ticket = new StringBuilder();
        if (isPriorityCustomer)
            ticket.append("PTC - ");
        else
            ticket.append("NTC - ");
        return String.valueOf(ticket.append(numberCustomer));
    }

    /**
     * This method is used to get the customers in queue.
     *
     * @return the number of customers in queue
     */
    public int getNumberOfCustomersInQueue() {
        return root.size();
    }

    /**
     * Gets the root heap that holds the tickets in the manager.
     *
     * @return The root heap containing the tickets.
     */
    public Heap<Ticket> getRoot() {
        return root;
    }

    /**
     * Finds the index of the last non-priority customer in the queue.
     *
     * @return The index of the last non-priority customer.
     */
    private int findLastPriorityCustomer() {
        for (int i = 0; i < root.size(); i++) {
            if (root.get(i) != null && !root.get(i).isPriorityCustomer()) return i;
        } return 0;
    }

    /**
     * Handles the menu option selected by the user.
     *
     * @param option The selected menu option.
     */
    private void handleMenuOption(int option) throws InterruptedException {
        switch (option) {
            case 1 -> addNewCustomerToQueue(true);
            case 2 -> addNewCustomerToQueue(false);
            case 3 -> System.out.println(ManagerString.getInstance().generateTicketList(root));
            case 4 -> serveAllCustomer(true);
            default -> System.out.println("\nInvalid choice. Please select a valid option.");
        }
    }

    /**
     * Runs the ticket program.
     */
    public void runProgram() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        boolean enable = false;

        while (!enable) {
            System.out.print(ManagerString.getInstance().printWelcomeMenu("BNC"));
            int option = scanner.nextInt();
            scanner.nextLine();
            handleMenuOption(option);

            if (option == 4) enable = true;
        }
    }
}