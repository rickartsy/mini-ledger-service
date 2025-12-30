package app.service;

import app.dto.AccountView;
import app.dto.TransactionView;
import app.exception.AccountNotFoundException;
import app.model.Account;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountService {
    private final Map<String, Account> accounts = new HashMap<>();
    private int idCounter = 0;

    public String createAccount(String ownerName) {
        idCounter++;
        String accountId = "ACC-" + idCounter;
        accounts.put(accountId, new Account(accountId, ownerName));
        return accountId;
    }

    public void deposit(String accountId, BigDecimal amount) {
        Account account = getAccountById(accountId);
        account.deposit(amount);
    }

    public void withdraw(String accountId, BigDecimal amount) {
        Account account = getAccountById(accountId);
        account.withdraw(amount);
    }


    public Account getAccountById(String accountId) {
        Account account = accounts.get(accountId);
        if (account == null) {
            throw new AccountNotFoundException(accountId);
        }
        return account;
    }

    public AccountView viewAccount(String accountId) {
        Account account = getAccountById(accountId);
        return new AccountView(
                account.getAccountId(),
                account.getOwnerName(),
                account.getBalance()
        );
    }

    public List<TransactionView> viewTransactions(String accountId) {
        Account account = getAccountById(accountId);
        return account.getTransactions().stream()
                .map(transaction -> new TransactionView(
                        transaction.getType(),
                        transaction.getAmount(),
                        transaction.getTimestamp()
                ))
                .toList();
    }

    public List<AccountView> listAccounts() {
        return accounts.values().stream()
                .map(account -> new AccountView(
                        account.getAccountId(),
                        account.getOwnerName(),
                        account.getBalance()
                ))
                .toList();
    }

}
