package Practica7_GestionDeTicketsBancarios;

import org.testng.annotations.Test;
import practica7_GestionDeTicketsBancarios.BankTicketsManager;

import static org.testng.AssertJUnit.assertEquals;

public class BankTicketsManagerAddCustomerTest {
    private BankTicketsManager bankBCB;

    @Test
    public void testInsertPriorityCustomer() {
        bankBCB = new BankTicketsManager();

        bankBCB.addNewCustomerToQueue(true);

        assertEquals(1, bankBCB.getNumberOfCustomersInQueue());
        assertEquals("[PTC - 1 [1]]\n", bankBCB.getRoot().toString());
    }

    @Test
    public void testInsertNonPriorityCustomer() {
        bankBCB = new BankTicketsManager();

        bankBCB.addNewCustomerToQueue(false);

        assertEquals(1, bankBCB.getNumberOfCustomersInQueue());
        assertEquals("[NTC - 1 [1]]\n", bankBCB.getRoot().toString());
    }

    @Test
    public void testInsertNormalCustomers() {
        bankBCB = new BankTicketsManager();

        for (int i = 0; i < 20; i++)
            bankBCB.addNewCustomerToQueue(false);

        assertEquals(bankBCB.getNumberOfCustomersInQueue(), 20);
        assertEquals("""
                [NTC - 1 [1]]
                [NTC - 2 [2]][NTC - 3 [3]]
                [NTC - 4 [4]][NTC - 5 [5]][NTC - 6 [6]][NTC - 7 [7]]
                [NTC - 8 [8]][NTC - 9 [9]][NTC - 10 [10]][NTC - 11 [11]][NTC - 12 [12]][NTC - 13 [13]][NTC - 14 [14]][NTC - 15 [15]]
                [NTC - 16 [16]][NTC - 17 [17]][NTC - 18 [18]][NTC - 19 [19]][NTC - 20 [20]]
                """, bankBCB.getRoot().toString());
    }

    @Test
    public void testInsertPriorityCustomers() {
        bankBCB = new BankTicketsManager();

        for (int i = 0; i < 15; i++)
            bankBCB.addNewCustomerToQueue(true);

        assertEquals(bankBCB.getNumberOfCustomersInQueue(), 15);
        assertEquals("""
                [PTC - 15 [0]]
                [PTC - 14 [0]][PTC - 13 [0]]
                [PTC - 12 [0]][PTC - 11 [0]][PTC - 10 [0]][PTC - 9 [0]]
                [PTC - 8 [0]][PTC - 7 [0]][PTC - 6 [0]][PTC - 5 [0]][PTC - 4 [0]][PTC - 3 [0]][PTC - 2 [0]][PTC - 1 [1]]
                """, bankBCB.getRoot().toString());
    }

    @Test
    public void testInsertPriorityAndNonPriorityCustomers() {
        bankBCB = new BankTicketsManager();

        bankBCB.addNewCustomerToQueue(true);
        bankBCB.addNewCustomerToQueue(false);

        assertEquals(2, bankBCB.getNumberOfCustomersInQueue());
        assertEquals("""
                [PTC - 1 [1]]
                [NTC - 2 [2]]
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
                [PTC - 1 [1]]
                [PTC - 3 [1]][PTC - 5 [2]]
                [PTC - 7 [3]][PTC - 9 [4]][NTC - 2 [2]][NTC - 4 [4]]
                [NTC - 6 [6]][NTC - 8 [8]][NTC - 10 [10]]
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
                [PTC - 1 [1]]
                [PTC - 3 [1]][PTC - 5 [2]]
                [PTC - 7 [3]][PTC - 9 [4]][PTC - 11 [5]][PTC - 13 [6]]
                [PTC - 15 [7]][PTC - 17 [8]][PTC - 19 [9]][NTC - 2 [2]][NTC - 4 [4]][NTC - 6 [6]][NTC - 8 [8]][NTC - 10 [10]]
                [NTC - 12 [12]][NTC - 14 [14]][NTC - 16 [16]][NTC - 18 [18]][NTC - 20 [20]]
                """, bankBCB.getRoot().toString());
    }
}
