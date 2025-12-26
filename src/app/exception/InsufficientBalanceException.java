package app.exception;

import java.math.BigDecimal;

public class InsufficientBalanceException extends AccountException{
    public InsufficientBalanceException(BigDecimal balance, BigDecimal amount) {
        super("Insufficient balance. Balance: " + balance + ", requested amount: " + amount);
    }
}
