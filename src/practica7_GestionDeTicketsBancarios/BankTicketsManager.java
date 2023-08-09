package practica7_GestionDeTicketsBancarios;

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
        return "Processing: " + root.poll();
    }

    /**
     * Serves all customers by processing and removing their tickets from the queue.
     *
     * @return A string containing messages for each processed customer's ticket.
     */
    public String serveAllCustomer() {
        StringBuilder sb = new StringBuilder();

        while (root.size() != 0) {
            Ticket ticket = root.poll();
            sb.append("Processing: ").append(ticket).append("\n");
        } return sb.toString();
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

    public String simulateBankAttention() {
        Heap<Ticket> copyHeap = new Heap<>(true);

        for (int i = 0; i < root.size(); i++) copyHeap.insert(root.get(i));

        StringBuilder sb = new StringBuilder();
        sb.append("Init Bank Attention:\n");

        while (copyHeap.size() > 0) {
            Ticket currentTicket = copyHeap.poll();
            sb.append("Now serving: ").append(currentTicket.getCode()).append("\n");
            if (copyHeap.size() > 0) sb.append("Next in line: ").append(copyHeap.peek().getCode()).append("\n");
            sb.append("-".repeat(60)).append("\n");
        } return sb.toString();
    }
}
