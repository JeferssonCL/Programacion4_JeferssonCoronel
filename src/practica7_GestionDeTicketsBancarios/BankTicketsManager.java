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


    private String generateTicket(boolean isPriorityCustomer) {
        StringBuilder ticket = new StringBuilder();
        if (isPriorityCustomer)
            ticket.append("PTC - ");
        else
            ticket.append("NTC - ");
        return String.valueOf(ticket.append(numberCustomer));
    }

    public void addNewCustomerToQueue(boolean isPriorityCustomer) {
        numberCustomer++;

        String code = generateTicket(isPriorityCustomer);
        Ticket newTicket = new Ticket(code, isPriorityCustomer, numberCustomer);

        if (isPriorityCustomer && root.size() > 0) {
            int lastPriorityIndex = findLastPriorityCustomer();
            root.moveElementToRight(lastPriorityIndex);
            newTicket.setNumberInQueue(lastPriorityIndex);
            root.updateValueAtIndex(lastPriorityIndex, newTicket);
        } else
            root.insert(newTicket);
    }

    public Heap<Ticket> getRoot() {
        return root;
    }

    private int findLastPriorityCustomer() {
        for (int i = 0; i < root.size(); i++) {
            if (root.get(i) != null && !root.get(i).isPriorityCustomer()) return i;
        } return 0;
    }
}
