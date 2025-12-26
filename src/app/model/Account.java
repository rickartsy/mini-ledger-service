package app.model;

import app.exception.InsufficientBalanceException;
import app.exception.InvalidAmountException;
import app.exception.InvalidNameException;

import java.math.BigDecimal;

public class Account {
    private final String accountId;
    private final String ownerName;
    private BigDecimal balance;

    public Account(String id, String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidNameException(name);
        }
        accountId = id;
        ownerName = name;
        balance = BigDecimal.ZERO;
    }

    public void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException(amount);
        }
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException(amount);
        }
        if (balance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException(balance, amount);
        }
        balance = balance.subtract(amount);
    }

    public String getAccountId() {
        return accountId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

}
