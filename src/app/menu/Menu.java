package app.menu;

import app.dto.AccountView;
import app.exception.AccountException;
import app.io.Input;
import app.service.AccountApplicationService;

import java.math.BigDecimal;

public class Menu {
    private boolean running = true;
    private final AccountApplicationService appServices;

    public Menu(AccountApplicationService appService) {
        this.appServices = appService;
    }

    public void start() {
        while (running) {
            printMenu();
            int choice = Input.readInt("Choose service: ");

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> viewAccountDetails();
                case 5 -> listAllAccounts();
                case 6 -> exit();
                default -> System.out.println("Invalid option.\n");
            }
        }
    }

    private void printMenu() {
        System.out.println("""
                === SERVICES ===
                1. Create new account
                2. Deposit money
                3. Withdraw money
                4. View account details
                5. List all account
                6. Exit
                """);
    }

    private String askAccountId() {
        return Input.readLine("Account ID: ");
    }

    private void createAccount() {
        String ownerName = Input.readLine("Name: ");
        try {
            String accountId = appServices.createAccount(ownerName);
            System.out.println("Successfully created a new account!");
            System.out.println("Account id: " + accountId + "\n");
        } catch (AccountException e) {
            System.err.println(e.getMessage());
        }
    }

    private void deposit() {
        String accountId = askAccountId();
        try {
            BigDecimal amount = Input.readBigDecimal("Amount to deposit: $");
            AccountView updated = appServices.deposit(accountId, amount);
            printAccountDetails(updated);
            System.out.println("Success!\n");
        } catch (AccountException e) {
            System.err.println(e.getMessage());
        }
    }

    private void withdraw() {
        String accountId = askAccountId();
        try {
            BigDecimal amount = Input.readBigDecimal("Amount to withdraw: $");
            AccountView updated = appServices.withdraw(accountId, amount);
            printAccountDetails(updated);
            System.out.println("Success!\n");
        } catch (AccountException e) {
            System.err.println(e.getMessage());
        }
    }

    private void viewAccountDetails() {
        String accountId = askAccountId();
        try {
            printAccountDetails(appServices.viewAccount(accountId));
        } catch (AccountException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void printAccountDetails(AccountView view) {
        System.out.printf("""
                        Account details
                            ID: %s
                            Owner: %s
                            Balance: $%s
                            %n""",
                view.accountId(),
                view.ownerName(),
                view.balance()
        );
    }

    private void listAllAccounts() {
        for (AccountView view : appServices.listAccounts()) {
            System.out.println(
                    view.accountId() + " | " + view.ownerName() + " | $" + view.balance()
            );
        }
        System.out.println();
    }

    private void exit() {
        running = false;
        System.out.println("Goodbye!");
    }

}
