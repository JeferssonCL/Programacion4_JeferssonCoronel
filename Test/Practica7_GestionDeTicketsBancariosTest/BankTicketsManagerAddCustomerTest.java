package Practica7_GestionDeTicketsBancariosTest;

import org.testng.annotations.Test;
import practica7_GestionDeTicketsBancarios.Parte1.BankTicketsManager;

import static org.testng.AssertJUnit.assertEquals;

public class BankTicketsManagerAddCustomerTest {
    private BankTicketsManager bankBCB;

    @Test
    public void testInsertPriorityCustomer() {
        bankBCB = new BankTicketsManager();

        bankBCB.addNewCustomerToQueue(true);

        assertEquals(1, bankBCB.getNumberOfCustomersInQueue());
        assertEquals("[PTC - 1]\n", bankBCB.getRoot().toString());
    }

    @Test
    public void testInsertNonPriorityCustomer() {
        bankBCB = new BankTicketsManager();

        bankBCB.addNewCustomerToQueue(false);

        assertEquals(1, bankBCB.getNumberOfCustomersInQueue());
        assertEquals("[NTC - 1]\n", bankBCB.getRoot().toString());
    }

    @Test
    public void testInsertNormalCustomers() {
        bankBCB = new BankTicketsManager();

        for (int i = 0; i < 20; i++)
            bankBCB.addNewCustomerToQueue(false);

        assertEquals(bankBCB.getNumberOfCustomersInQueue(), 20);
        assertEquals("""
                [NTC - 1]
                [NTC - 2][NTC - 3]
                [NTC - 4][NTC - 5][NTC - 6][NTC - 7]
                [NTC - 8][NTC - 9][NTC - 10][NTC - 11][NTC - 12][NTC - 13][NTC - 14][NTC - 15]
                [NTC - 16][NTC - 17][NTC - 18][NTC - 19][NTC - 20]
                """, bankBCB.getRoot().toString());
    }

    @Test
    public void testInsertPriorityCustomers() {
        bankBCB = new BankTicketsManager();

        for (int i = 0; i < 15; i++)
            bankBCB.addNewCustomerToQueue(true);

        assertEquals(bankBCB.getNumberOfCustomersInQueue(), 15);
        assertEquals("""
                [PTC - 15]
                [PTC - 10][PTC - 13]
                [PTC - 7][PTC - 9][PTC - 11][PTC - 12]
                [PTC - 1][PTC - 4][PTC - 3][PTC - 8][PTC - 2][PTC - 6][PTC - 5][PTC - 14]
                """, bankBCB.getRoot().toString());
    }

    @Test
    public void testInsertPriorityAndNonPriorityCustomers() {
        bankBCB = new BankTicketsManager();

        bankBCB.addNewCustomerToQueue(true);
        bankBCB.addNewCustomerToQueue(false);

        assertEquals(2, bankBCB.getNumberOfCustomersInQueue());
        assertEquals("""
                [PTC - 1]
                [NTC - 2]
                """, bankBCB.getRoot().toString());
    }

    @Test
    public void testInsertMultipleCustomers() {
        bankBCB = new BankTicketsManager();

        for (int i = 0; i < 10; i++) {
            bankBCB.addNewCustomerToQueue(i % 2 == 0);
        }

        assertEquals(10, bankBCB.getNumberOfCustomersInQueue());
        assertEquals("""
                [PTC - 1]
                [PTC - 3][PTC - 7]
                [PTC - 5][NTC - 8][NTC - 6][NTC - 4]
                [PTC - 9][NTC - 2][NTC - 10]
                """, bankBCB.getRoot().toString());
    }

    @Test
    public void testInsertPriorityAndNonPriorityCustomersToVerifyTheOrder() {
        bankBCB = new BankTicketsManager();

        for (int i = 0; i < 10; i++) {
            bankBCB.addNewCustomerToQueue(true);
            bankBCB.addNewCustomerToQueue(false);
        }

        assertEquals(bankBCB.getNumberOfCustomersInQueue(), 20);

        assertEquals("""
                [PTC - 1]
                [PTC - 3][PTC - 7]
                [PTC - 5][PTC - 11][PTC - 13][PTC - 15]
                [PTC - 9][PTC - 19][NTC - 10][NTC - 8][NTC - 12][NTC - 6][NTC - 14][NTC - 4]
                [PTC - 17][NTC - 2][NTC - 18][NTC - 16][NTC - 20]
                """, bankBCB.getRoot().toString());
    }
}
