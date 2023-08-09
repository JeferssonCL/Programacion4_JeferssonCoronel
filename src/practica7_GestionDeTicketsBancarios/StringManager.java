package practica7_GestionDeTicketsBancarios;

import java.util.Random;

/**
 * A utility class for managing and generating various strings related to ticket management and display.
 */
public class StringManager {
    private static StringManager instance;
    private final Random random;

    private StringManager() {
        random = new Random();
    }

    /**
     * Returns the singleton instance of the StringManager class.
     *
     * @return The singleton instance.
     */
    public static StringManager getInstance() {
        if (instance == null) instance = new StringManager();
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
     * @param input The input string to be padded.
     * @return The padded string with added spaces.
     */
    private String padSpaces(String input) {
        int spacesToAdd = 33 - input.length();
        return " ".repeat(Math.max(0, spacesToAdd));
    }

    /**
     * Simulates a bank service ticket for display purposes.
     *
     * @return A formatted string representing the ticket information.
     */
    public String generateSimulationBankTicket(String code, boolean isPriorityCustomer) {
        return "==================================================\n" +
                "             BANK SERVICE TICKET\n" +
                "==================================================\n" +
                "\n" +
                "Ticket Number: " + code + "\n" +
                "Priority:       " + (isPriorityCustomer ? "Priority Customer" : "Normal Customer") + "\n" +
                "Please keep this ticket for future reference.\n" +
                "\n" +
                "==================================================";
    }

    /**
     * Generates a formatted welcome menu for a bank.
     *
     * @param name The name of the bank.
     * @return A formatted string representing the welcome menu.
     */
    public String printWelcomeMenu(String name) {
        return "=================================\n" +
                "      Welcome to " + name + " Bank\n" +
                "=================================\n" +
                "Please select an option:\n" +
                "1. Get Priority Ticket\n" +
                "2. Get a Standard Ticket\n" +
                "3. Exit\n" +
                "=================================\n";
    }

    /**
     * Generates a formatted list of tickets for display.
     *
     * @param tickets The heap of tickets to be listed.
     * @return A formatted string representing the ticket list.
     */
    public String generateTicketList(Heap<Ticket> tickets) {
        StringBuilder ticketList = new StringBuilder();
        ticketList.append("=============================\n");
        ticketList.append("       Ticket List\n");
        ticketList.append("=============================\n");

        for (int i = 0; i < tickets.size(); i++) {
            ticketList.append(String.format("%d. %s\n", i + 1, tickets.get(i)));
        }

        ticketList.append("=============================");
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

        return "╔═══════════════════════════════════╗\n" +
                "║      Ticket System - " + bankName + "      ║\n" +
                "╠═══════════════════════════════════╣\n" +
                "║   Current Ticket:   " + currentTicket + padSpaces(currentTicket) + "║\n" +
                "║   Desk:             " + currentDesk + padSpaces(String.valueOf(currentDesk)) + "║\n" +
                "║                                    ║\n" +
                "║   Next Ticket:      " + nextTicket + padSpaces(nextTicket) + "║\n" +
                "║   Estimated Wait:   " + estimatedWait + " minutes" + padSpaces(estimatedWait + " minutes") + "║\n" +
                "╚═══════════════════════════════════╝";
    }
}
