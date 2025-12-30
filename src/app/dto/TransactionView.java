package app.dto;

import app.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionView (
        TransactionType type,
        BigDecimal amount,
        LocalDateTime timestamp
) { }
