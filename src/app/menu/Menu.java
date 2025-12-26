package app.menu;

import app.dto.AccountView;
import app.exception.AccountException;
import app.io.Input;
import app.service.AccountService;

import java.math.BigDecimal;

public class Menu {
    private static boolean running = true;
    private static final AccountService services = new AccountService();

    public static void start() {
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

    private static void printMenu() {
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

    private static String askAccountId() {
        return Input.readLine("Account ID: ");
    }

    private static void createAccount() {
        String ownerName = Input.readLine("Name: ");
        try {
            String accountId = services.createAccount(ownerName);
            System.out.println("Successfully created a new account!");
            System.out.println("Account id: " + accountId + "\n");
        } catch (AccountException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void deposit() {
        String accountId = askAccountId();
        try {
            AccountView view = services.viewAccount(accountId);
            printAccountDetails(view);
            BigDecimal amount = Input.readBigDecimal("Amount to deposit: $");
            services.deposit(accountId, amount);
            System.out.println("Success!\n");
        } catch (AccountException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void withdraw() {
        String accountId = askAccountId();
        try {
            AccountView view = services.viewAccount(accountId);
            printAccountDetails(view);
            BigDecimal amount = Input.readBigDecimal("Amount to withdraw: $");
            services.withdraw(accountId, amount);
            System.out.println("Success!\n");
        } catch (AccountException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void viewAccountDetails() {
        String accountId = askAccountId();
        try {
            AccountView view = services.viewAccount(accountId);
            printAccountDetails(view);
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

    private static void listAllAccounts() {
        for (AccountView view : services.listAccounts()) {
            System.out.println(
                    view.accountId() + " | " + view.ownerName() + " | $" + view.balance()
            );
        }
        System.out.println();
    }

    private static void exit() {
        running = false;
        System.out.println("Goodbye!");
    }

}
