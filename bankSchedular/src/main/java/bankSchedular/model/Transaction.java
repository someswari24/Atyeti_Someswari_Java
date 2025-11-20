package bankSchedular.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class Transaction {
    private final String txnId;
    private final TransactionType type;
    private final String fromAccount;
    private final String toAccount;
    private final long amountCents;
    private final int priority;
    private final Instant createdAt=Instant.now();

}
