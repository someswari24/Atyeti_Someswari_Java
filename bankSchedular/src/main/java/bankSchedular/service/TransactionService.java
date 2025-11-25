package bankSchedular.service;

import bankSchedular.engine.BankEngine;
import bankSchedular.fraud.FraudDetector;
import bankSchedular.model.Account;
import bankSchedular.model.Transaction;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class TransactionService {
    private final PriorityBlockingQueue<Transaction> queue = new PriorityBlockingQueue<>(1000, (a, b) -> Integer.compare(a.getPriority(), b.getPriority()));
    private final BankEngine bankEngine;
    private final FraudDetector fraudDetector;
    private final Logger logger = Logger.getLogger("Transaction Service");
    private final AtomicBoolean running = new AtomicBoolean(false);

    @PostConstruct
    public void init() {
        startWorker();
    }

    public void submitTransaction(Transaction transaction) {
        queue.offer(transaction);
    }

    private void startWorker() {
        if (running.compareAndSet(false, true)) {
            Thread thread = new Thread(() -> {
                while (running.get()) {
                    try {
                        Transaction transaction = queue.take();
                        if (fraudDetector.detectFraud(transaction)) {
                            logger.warning("fraud transaction" + transaction.getTxnId());
                            continue;
                        }
                        boolean result = bankEngine.process(transaction);
                        if (!result) logger.warning("Transaction failed: " + transaction.getTxnId());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }, "Transaction Worker");
            thread.setDaemon(true);
            thread.start();
        }
    }

    public List<String> listAccountIds() {
        List<String> out = new ArrayList<>();
        bankEngine.getLedger().forEach(e->{});
        return out;
    }
//    public List<String> listAccountIds() {
//        return accountRepository.findAll()
//                .stream()
//                .map(Account::getAccountId)
//                .toList();
//    }
}
