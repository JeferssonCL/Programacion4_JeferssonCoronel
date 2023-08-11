package Practica7_GestionDeTicketsBancariosTest;

import org.testng.annotations.Test;
import practica7_GestionDeTicketsBancarios.Parte1.BankTicketsManager;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class BankTicketsManagerAttendOrDeleteCustomerTest {
    private BankTicketsManager bankBCB;

    @Test
    public void testServeOneCustomer() {
        bankBCB = new BankTicketsManager();

        bankBCB.addNewCustomerToQueue(true);
        String processingMessage = bankBCB.serveOneCustomer();

        assertTrue(processingMessage.contains("PTC - 1"));
    }

    @Test
    public void testServeAllCustomers() throws InterruptedException {
        bankBCB = new BankTicketsManager();

        bankBCB.addNewCustomerToQueue(false);
        bankBCB.addNewCustomerToQueue(false);
        bankBCB.addNewCustomerToQueue(true);
        String processingMessages = bankBCB.serveAllCustomer(false);

        assertTrue(processingMessages.contains("PTC - 3"));
        assertTrue(processingMessages.contains("NTC - 1"));
        assertTrue(processingMessages.contains("NTC - 2"));
    }

    @Test
    public void testServeOneCustomerPriorityFirst() {
        bankBCB = new BankTicketsManager();

        bankBCB.addNewCustomerToQueue(false);
        bankBCB.addNewCustomerToQueue(false);
        bankBCB.addNewCustomerToQueue(false);
        bankBCB.addNewCustomerToQueue(false);
        bankBCB.addNewCustomerToQueue(false);
        bankBCB.addNewCustomerToQueue(true);
        assertEquals(bankBCB.getNumberOfCustomersInQueue(), 6);

        String processingMessage = bankBCB.serveOneCustomer();

        assertTrue(processingMessage.contains("PTC - 6"));
        assertEquals(bankBCB.getNumberOfCustomersInQueue(), 5);
    }

    @Test
    public void testServeAllCustomersPriorityFirst() throws InterruptedException {
        bankBCB = new BankTicketsManager();

        bankBCB.addNewCustomerToQueue(true);
        bankBCB.addNewCustomerToQueue(false);
        assertEquals(bankBCB.getNumberOfCustomersInQueue(), 2);

        String processingMessages = bankBCB.serveAllCustomer(false);

        assertTrue(processingMessages.contains("PTC - 1"));
        assertTrue(processingMessages.contains("NTC - 2"));
        assertEquals(bankBCB.getNumberOfCustomersInQueue(), 0);
    }

    @Test
    public void testServeAllCustomersNonPriorityFirst() throws InterruptedException {
        bankBCB = new BankTicketsManager();

        bankBCB.addNewCustomerToQueue(false);
        bankBCB.addNewCustomerToQueue(true);
        assertEquals(bankBCB.getNumberOfCustomersInQueue(), 2);

        String processingMessages = bankBCB.serveAllCustomer(false);

        assertTrue(processingMessages.contains("NTC - 1"));
        assertTrue(processingMessages.contains("PTC - 2"));
        assertEquals(bankBCB.getNumberOfCustomersInQueue(), 0);
    }
}
