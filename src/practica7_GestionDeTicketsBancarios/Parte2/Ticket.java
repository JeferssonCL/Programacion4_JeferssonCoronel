package practica7_GestionDeTicketsBancarios.Parte2;

public class Ticket implements Comparable<Ticket> {
    private final int id;
    private final boolean isPriority;

    public Ticket(int id, boolean isPriority) {
        this.id = id;
        this.isPriority = isPriority;
    }

    @Override
    public int compareTo(Ticket other) {
        if (this.isPriority && !other.isPriority)
            return -1;
        else if (!this.isPriority && other.isPriority)
            return 1;
        else
            return Integer.compare(this.id, other.id);

    }

    /**
     * Returns a string representation of the ticket.
     *
     * @return The ticket code along with its position in the queue.
     */
    @Override
    public String toString() {
        return id + "";
    }
}