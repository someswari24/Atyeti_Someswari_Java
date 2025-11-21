package bankSchedular.jobs;

import bankSchedular.engine.BankEngine;
import bankSchedular.model.Account;
import bankSchedular.model.LedgerEntry;
import bankSchedular.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

@Component
@AllArgsConstructor
public class ReconciliationJobs implements Runnable {
    private final AccountRepository accountRepository;
    private final BankEngine bankEngine;

    @Override
    public void run() {
        var ledger = bankEngine.getLedger();
        Map<String,Long>ledgerBalances=new HashMap<>();
        for (LedgerEntry entry:ledger) {
            ledgerBalances.merge(entry.getAccountId(), entry.getAmountCents(), Long::sum);
        }
        for (Account account:accountRepository.all()){
            long accountBalance=account.getBalanceCents();
            long ledgerBal=ledgerBalances.getOrDefault(account.getAccountId(),0L);
            if (accountBalance != ledgerBal) {
                System.err.printf("Reconciliation mismatch %s: acc=%.2f ledger=%.2f\n",account.getAccountId(), accountBalance/100.0, ledgerBal/100.0);
            }
        }
    }
}
