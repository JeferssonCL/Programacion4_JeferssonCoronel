package Practica7_GestionDeTicketsBancarios;

import org.testng.annotations.Test;
import practica7_GestionDeTicketsBancarios.BankTicketsManager;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class BankTicketsManagerAttendOrDeleteCustomerTest {

    private BankTicketsManager bankBCB;

    @Test
    public void testServeOneCustomer() {
        bankBCB = new BankTicketsManager();

        bankBCB.addNewCustomerToQueue(true);
        String processingMessage = bankBCB.serveOneCustomer();
        System.out.println(processingMessage);
        assertTrue(processingMessage.startsWith("Processing: PTC - "));
    }

    @Test
    public void testServeAllCustomers() {
        bankBCB = new BankTicketsManager();

        bankBCB.addNewCustomerToQueue(true);
        bankBCB.addNewCustomerToQueue(false);

        String processingMessages = bankBCB.serveAllCustomer();
        assertTrue(processingMessages.contains("Processing: PTC -"));
        assertTrue(processingMessages.contains("Processing: NTC -"));
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

        String processingMessage = bankBCB.serveOneCustomer();
        assertTrue(processingMessage.startsWith("Processing: PTC -"));
        assertEquals("Processing: PTC - 6", processingMessage);
        assertEquals(bankBCB.getNumberOfCustomersInQueue(), 5);
    }

    @Test
    public void testServeAllCustomersPriorityFirst() {
        bankBCB = new BankTicketsManager();

        bankBCB.addNewCustomerToQueue(true);
        bankBCB.addNewCustomerToQueue(false);

        String processingMessages = bankBCB.serveAllCustomer();
        assertTrue(processingMessages.contains("Processing: PTC -"));
        assertTrue(processingMessages.contains("Processing: NTC -"));
        assertEquals(bankBCB.getNumberOfCustomersInQueue(), 0);
    }

    @Test
    public void testServeAllCustomersNonPriorityFirst() {
        bankBCB = new BankTicketsManager();

        bankBCB.addNewCustomerToQueue(false);
        bankBCB.addNewCustomerToQueue(true);

        String processingMessages = bankBCB.serveAllCustomer();
        assertTrue(processingMessages.contains("Processing: NTC -"));
        assertTrue(processingMessages.contains("Processing: PTC -"));
        assertEquals(bankBCB.getNumberOfCustomersInQueue(), 0);
    }
}
