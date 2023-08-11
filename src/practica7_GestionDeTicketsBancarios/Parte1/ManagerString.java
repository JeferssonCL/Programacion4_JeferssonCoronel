package practica7_GestionDeTicketsBancarios.Parte1;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * A utility class for managing and generating various strings related to ticket management and display.
 */
public class ManagerString {
    private static ManagerString instance;
    private final Random random;

    private ManagerString() {
        random = new Random();
    }

    /**
     * Returns the singleton instance of the StringManager class.
     *
     * @return The singleton instance.
     */
    public static ManagerString getInstance() {
        if (instance == null) instance = new ManagerString();
        return instance;
    }

    /**
     * Generates a random integer in the specified range.
     *
     * @param maxRange The upper bound of the range (inclusive).
     * @return A randomly generated integer.
     */
    private int generateAleatoryNumber(int maxRange) {
        return random.nextInt(maxRange + 1);
    }

    /**
     * Generates spaces to pad the input string to a specified length.
     *
     * @param text The input string to be padded.
     * @return The padded string with added spaces.
     */
    private String padSpaces(String text) {
        int spacesToAdd = 15 - text.length();
        return " ".repeat(Math.max(0, spacesToAdd));
    }

    /**
     * Generates a formatted welcome menu for a bank.
     *
     * @param name The name of the bank.
     * @return A formatted string representing the welcome menu.
     */
    public String printWelcomeMenu(String name) {
        return ManagerColor.YELLOW +
                "\n=================================\n" +
                "      Welcome to " + name + " Bank\n" +
                "=================================\n" +
                "Please select an option:\n" +
                "1. Get Priority Ticket\n" +
                "2. Get a Standard Ticket\n" +
                "3. Show customer queue\n" +
                "4. Start customer service\n" +
                "=================================\n" +
                ManagerColor.RESET + " --> ";
    }

    /**
     * Generates a formatted list of tickets for display.
     *
     * @param tickets The heap of tickets to be listed.
     * @return A formatted string representing the ticket list.
     */
    public String generateTicketList(Heap<Ticket> tickets) {
        StringBuilder ticketList = new StringBuilder();

        System.out.println("\n* List of customers that are in the queue to be served ...\n");

        ticketList.append("=================================\n");
        ticketList.append(ManagerColor.CYAN + "          Ticket List\n" + ManagerColor.RESET);
        ticketList.append("=================================\n");

        for (int i = 0; i < tickets.size(); i++) ticketList.append(String.format("%d. %s\n", i + 1, tickets.get(i)));

        ticketList.append("=================================");
        return ticketList.toString();
    }

    /**
     * Generates a formatted attention ticket for display purposes.
     *
     * @param currentTicket The current ticket being served.
     * @param nextTicket    The next ticket in the queue.
     * @param bankName      The name of the bank.
     * @return A formatted string representing the attention ticket information.
     */
    public String generateSimulationAttentionTicket(String currentTicket, String nextTicket, String bankName) {
        int currentDesk = generateAleatoryNumber(5);
        int estimatedWait = generateAleatoryNumber(15);

        return "--------------------------------------\n" +
                "|      " + ManagerColor.CYAN + "Ticket System - " + bankName + "      " + ManagerColor.RESET + "|\n" +
                "|------------------------------------|\n" +
                "|   Current Ticket:   " + currentTicket + padSpaces(currentTicket) + "|\n" +
                "|   Desk:             " + currentDesk + padSpaces(String.valueOf(currentDesk)) + "|\n" +
                "|                                    |\n" +
                "|   Next Ticket:      " + nextTicket + padSpaces(nextTicket) + "|\n" +
                "|   Estimated Wait:   " + estimatedWait + " minutes" + padSpaces(estimatedWait + " minutes") + "|\n" +
                "--------------------------------------";
    }

    /**
     * Generates a visual representation of customer service attention using symbols.
     * <p>
     * This method displays a sequence of symbols to represent customer service attention.
     * The symbols are used to mimic an attention-seeking animation.
     *
     * @throws InterruptedException If the thread is interrupted during sleep.
     */
    public void generateCustomerServiceAttention() throws InterruptedException {
        System.out.println("\n* Starting customer service ...");
        System.out.println("\t\t\t↺");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("\t\t\t↻");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("\t\t\t↺");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("\t\t\t↻\n");
    }
}
