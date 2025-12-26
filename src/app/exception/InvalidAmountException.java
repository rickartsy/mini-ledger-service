package app.exception;

import java.math.BigDecimal;

public class InvalidAmountException extends AccountException {
    public InvalidAmountException(BigDecimal amount) {
        super("Amount must be greater than zero. Provided: '" + amount + "'");
    }
}
