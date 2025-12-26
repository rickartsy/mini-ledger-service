package app.dto;

import java.math.BigDecimal;

public record AccountView(
        String accountId,
        String ownerName,
        BigDecimal balance
) { }
