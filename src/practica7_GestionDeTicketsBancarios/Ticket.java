package practica7_GestionDeTicketsBancarios;

/**
 * The Ticket class represents a customer ticket in a bank queue.
 */
public class Ticket implements Comparable<Ticket> {

    private final String code;
    private int numberInQueue;
    private final boolean isPriorityCustomer;

    /**
     * Constructs a new Ticket instance with the provided attributes.
     *
     * @param code The unique code of the ticket.
     * @param isPriorityCustomer Indicates whether the customer is a priority customer.
     * @param numberInQueue The position of the ticket in the queue.
     */
    public Ticket(String code, boolean isPriorityCustomer, int numberInQueue) {
        this.code = code;
        this.isPriorityCustomer = isPriorityCustomer;
        this.numberInQueue = numberInQueue;
    }

    /**
     * Compares this ticket to another ticket based on their positions in the queue.
     *
     * @param otherTicket The other ticket to compare with.
     * @return A negative integer, zero, or a positive integer as this ticket is less than, equal to,
     *         or greater than the other ticket in the queue.
     */
    @Override
    public int compareTo(Ticket otherTicket) {
        return Integer.compare(this.numberInQueue, otherTicket.getNumberInQueue());
    }

    /**
     * Gets the position of the ticket in the queue.
     *
     * @return The position of the ticket in the queue.
     */
    public int getNumberInQueue() {
        return numberInQueue;
    }

    /**
     * Checks if the customer associated with the ticket is a priority customer.
     *
     * @return True if the customer is a priority customer, false otherwise.
     */
    public boolean isPriorityCustomer() {
        return isPriorityCustomer;
    }

    /**
     * Sets the new position of the ticket in the queue.
     *
     * @param numberInQueue The new position of the ticket in the queue.
     */
    public void setNumberInQueue(int numberInQueue) {
        this.numberInQueue = numberInQueue;
    }

    /**
     * Retrieves the ticket code.
     *
     * @return The code of the ticket.
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns a string representation of the ticket.
     *
     * @return The ticket code along with its position in the queue.
     */
    @Override
    public String toString() {
        return code;
    }
}
