package bankSchedular.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Getter
public class LedgerEntry {
    private final String entryId = java.util.UUID.randomUUID().toString();
    private final String txnId;
    private final String accountId;
    private final long amountCents;
    private final Instant timestamp = Instant.now();

}
