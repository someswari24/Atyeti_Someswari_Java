package bankSchedular.engine;

import bankSchedular.concurrency.DistributedLockmanager;
import bankSchedular.model.Account;
import bankSchedular.model.LedgerEntry;
import bankSchedular.model.Transaction;
import bankSchedular.model.TransactionType;
import bankSchedular.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

@Component
@AllArgsConstructor
public class BankEngine {
    private final AccountRepository accountRepository;
    private final DistributedLockmanager lockManager;
    @Getter
    private final Queue<LedgerEntry> ledger = new ConcurrentLinkedQueue<>();
    private final Logger logger = Logger.getLogger("Bank Engine");

    public boolean process(Transaction transaction) {
        if (transaction.getType() == TransactionType.CREDIT) return credit(transaction);
        if (transaction.getType() == TransactionType.DEBIT) return debit(transaction);
        if (transaction.getType() == TransactionType.TRANSFER) return transfer(transaction);
        return false;
    }

    private boolean credit(Transaction transaction) {
        Account account = accountRepository.find(transaction.getToAccount());
        if (account == null) return false;
        String lockKey = "account : " + account.getAccountId();
        if (!lockManager.acquire(lockKey, 2000)) return false;
        try {
            account.getLock().lock();
            try {
                long newBalance = account.addAndGet(transaction.getAmountCents());
                ledger.add(new LedgerEntry(transaction.getTxnId(), account.getAccountId(), transaction.getAmountCents()));
                logger.info(String.format("CREDIT %s -> %s: %.2f", transaction.getTxnId(), account.getAccountId(), transaction.getAmountCents() / 100.0));
                return true;
            } finally {
                account.getLock().unlock();
            }
        } finally {
            lockManager.release(lockKey);
        }
    }

    private boolean debit(Transaction transaction) {
        Account account = accountRepository.find(transaction.getFromAccount());
        if (account == null) return false;
        String lockKey = "account :" + account.getAccountId();
        if (!lockManager.acquire(lockKey, 2000)) return false;
        try {
            account.getLock().lock();
            try {
                long currentBalance = account.getBalanceCents();
                if (currentBalance < transaction.getAmountCents()) return false;
                long newBalance = account.addAndGet(-transaction.getAmountCents());
                ledger.add(new LedgerEntry(transaction.getTxnId(), account.getAccountId(), -transaction.getAmountCents()));
                logger.info(String.format("DEBIT %s -> %s : %.2f", transaction.getTxnId(), account.getAccountId(), transaction.getAmountCents() / 100.0));
                return true;
            } finally {
                account.getLock().unlock();
            }
        } finally {
            lockManager.release(lockKey);
        }
    }

    private boolean transfer(Transaction transaction) {
        Account account1 = accountRepository.find(transaction.getFromAccount());
        Account account2 = accountRepository.find(transaction.getToAccount());
        if (account1 == null || account2 == null) return false;
        List<Account> list = new ArrayList<>();
        list.add(account1);
        list.add(account2);
        list.sort(Comparator.comparing(Account::getAccountId));
        List<String> keys = new ArrayList<>();
        for (Account a : list) {
            keys.add("account:" + a.getAccountId());
        }

        for (String k : keys) {
            if (!lockManager.acquire(k, 2000)) {
                for (String r : keys)
                    lockManager.release(r);
                return false;
            }
        }
        try {
            for (Account a : list) a.getLock().lock();
            try {
                Account fromAccount = accountRepository.find(transaction.getFromAccount());
                Account toAccount = accountRepository.find(transaction.getToAccount());
                if (fromAccount.getBalanceCents() < transaction.getAmountCents()) return false;
                fromAccount.addAndGet(-transaction.getAmountCents());
                toAccount.addAndGet(transaction.getAmountCents());
                ledger.add(new LedgerEntry(transaction.getTxnId(), fromAccount.getAccountId(), -transaction.getAmountCents()));
                ledger.add(new LedgerEntry(transaction.getTxnId(), toAccount.getAccountId(), transaction.getAmountCents()));
                logger.info(String.format("TRANSFER %s %s -> %s: %.2f", transaction.getTxnId(), fromAccount.getAccountId(), toAccount.getAccountId(), transaction.getAmountCents() / 100.0));
                return true;
            } finally {
                for (Account a : list) a.getLock().unlock();
            }
        } finally {
            for (String k : keys) lockManager.release(k);
        }
    }

    public List<LedgerEntry> snapshotLedger() {
        return new ArrayList<>(ledger);
    }
}
