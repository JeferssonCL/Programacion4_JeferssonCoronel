package practica7_GestionDeTicketsBancarios;

public class Main {

    public static void main(String[] args) {
        BankTicketsManager bankTicketsManager = new BankTicketsManager();

        bankTicketsManager.addNewCustomerToQueue(false);
        bankTicketsManager.addNewCustomerToQueue(false);
        bankTicketsManager.addNewCustomerToQueue(true);
        bankTicketsManager.addNewCustomerToQueue(false);
        bankTicketsManager.addNewCustomerToQueue(false);
        bankTicketsManager.addNewCustomerToQueue(false);
        bankTicketsManager.addNewCustomerToQueue(false);
        bankTicketsManager.addNewCustomerToQueue(false);
        bankTicketsManager.addNewCustomerToQueue(false);
        bankTicketsManager.addNewCustomerToQueue(false);
        bankTicketsManager.addNewCustomerToQueue(false);


        System.out.println(bankTicketsManager.getRoot());

        bankTicketsManager.addNewCustomerToQueue(true);
        bankTicketsManager.addNewCustomerToQueue(false);

        System.out.println(bankTicketsManager.getRoot());

        bankTicketsManager.addNewCustomerToQueue(false);
        bankTicketsManager.addNewCustomerToQueue(false);
        bankTicketsManager.addNewCustomerToQueue(false);
        bankTicketsManager.addNewCustomerToQueue(false);
        bankTicketsManager.addNewCustomerToQueue(false);

        System.out.println(bankTicketsManager.getRoot());


        bankTicketsManager.addNewCustomerToQueue(true);
        bankTicketsManager.addNewCustomerToQueue(true);
        bankTicketsManager.addNewCustomerToQueue(true);
        bankTicketsManager.addNewCustomerToQueue(true);
        bankTicketsManager.addNewCustomerToQueue(false);

        System.out.println(bankTicketsManager.getRoot());
    }
}
