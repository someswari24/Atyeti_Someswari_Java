package bankSchedular.producer;

import bankSchedular.model.Transaction;
import bankSchedular.model.TransactionType;
import bankSchedular.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
@AllArgsConstructor
public class TransactionProducer {
    private final TransactionService transactionService;
    private final Random random = new Random();

    public void simulate(int count) {
        List<String> ids = transactionService.listAccountIds();
        for (int i = 0; i < count; i++) {
            String trans = UUID.randomUUID().toString();
            int pick = random.nextInt(10);
            long amount = (random.nextInt(2000) + 100) * 100L;
            Transaction transaction;
            if (pick < 4) {
                String fromAccount = ids.get(random.nextInt(ids.size()));
                String toAccount = ids.get(random.nextInt(ids.size()));
                while (toAccount.equals(fromAccount)) toAccount = ids.get(random.nextInt(ids.size()));
                transaction = new Transaction(trans, TransactionType.TRANSFER, fromAccount, toAccount, amount, 3);
            } else if (pick < 7) {
                String fromAccount = ids.get(random.nextInt(ids.size()));
                transaction = new Transaction(trans, TransactionType.DEBIT, fromAccount, null, amount, 3);
            } else {
                String toAccount = ids.get(random.nextInt(ids.size()));
                transaction = new Transaction(trans, TransactionType.CREDIT, null, toAccount, amount, 3);
            }
            transactionService.submitTransaction(transaction);
            try {
                Thread.sleep(random.nextInt(30));
            } catch (InterruptedException ignored) {
            }
        }
    }
}
