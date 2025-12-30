package app.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private final TransactionType type;
    private final BigDecimal amount;
    private final LocalDateTime timestamp;

    public Transaction(TransactionType type, BigDecimal amount) {
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }
}
