package bankSchedular.service;

import bankSchedular.engine.BankEngine;
import bankSchedular.model.Account;
import bankSchedular.model.LedgerEntry;
import bankSchedular.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ReconciliationService {

    private final AccountRepository accountRepository;
    private final BankEngine bankEngine;

    public Map<String, String> reconcileNow() {
        List<LedgerEntry> ledger = bankEngine.snapshotLedger();

        Map<String, Long> ledgerTotals = new HashMap<>();
        for (LedgerEntry e : ledger) {
            ledgerTotals.merge(e.getAccountId(), e.getAmountCents(), Long::sum);
        }

        Map<String, String> result = new HashMap<>();
        for (Account a : accountRepository.all()) {
            long accBal = a.getBalanceCents();
            long led = ledgerTotals.getOrDefault(a.getAccountId(), 0L);
            if (accBal != led) {
                result.put(a.getAccountId(),
                        String.format("MISMATCH account=%.2f ledger=%.2f", accBal/100.0, led/100.0));
            } else {
                result.put(a.getAccountId(), "OK");
            }
        }
        return result;
    }

    @Scheduled(fixedRateString = "${reconcile.rate.ms:60000}")
    public void scheduledReconcile() {
        Map<String, String> summary = reconcileNow();
        long mismatches = summary.values().stream().filter(v -> v.startsWith("MISMATCH")).count();
        if (mismatches > 0) {
            System.err.println("Reconciliation found " + mismatches + " mismatches.");
            summary.forEach((k,v) -> {
                if (!"OK".equals(v)) System.err.println(k + " -> " + v);
            });
        } else {
            System.out.println("Reconciliation: all OK");
        }
    }
}

