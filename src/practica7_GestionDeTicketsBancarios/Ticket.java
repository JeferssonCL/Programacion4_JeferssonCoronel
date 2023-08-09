package practica7_GestionDeTicketsBancarios;

public class Ticket implements Comparable<Ticket> {

    private String code;
    private int numberInQueue;
    private boolean isPriorityCustomer;

    public Ticket(String code, boolean isPriorityCustomer, int numberInQueue) {
        this.code = code;
        this.isPriorityCustomer = isPriorityCustomer;
        this.numberInQueue = numberInQueue;
    }

    @Override
    public int compareTo(Ticket otherTicket) {
        return Integer.compare(this.numberInQueue, otherTicket.getNumberInQueue());
    }

    public String getCode() {
        return code;
    }

    public int getNumberInQueue() {
        return numberInQueue;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isPriorityCustomer() {
        return isPriorityCustomer;
    }

    public void setPriorityCustomer(boolean priorityCustomer) {
        isPriorityCustomer = priorityCustomer;
    }

    public void setNumberInQueue(int numberInQueue) {
        this.numberInQueue = numberInQueue;
    }

    @Override
    public String toString() {
        return code + " [" + numberInQueue + "]";
    }
}
