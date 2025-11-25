package bankSchedular.controller;

import bankSchedular.model.Transaction;
import bankSchedular.model.TransactionType;
import bankSchedular.producer.TransactionProducer;
import bankSchedular.service.AccountService;
import bankSchedular.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private final AccountService accountService;

    @PostMapping("/credit")
    public ResponseEntity<String> credit(@RequestParam String toAccount,@RequestParam double amount) {
        String id = UUID.randomUUID().toString();
        Transaction transaction = new Transaction(id,TransactionType.CREDIT,null,toAccount,(long)(amount*100),3);
        transactionService.submitTransaction(transaction);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/debit")
    public ResponseEntity<String> debit(@RequestParam String fromAccount,@RequestParam double amount) {
        String id = UUID.randomUUID().toString();
        Transaction transaction = new Transaction(id,TransactionType.DEBIT,fromAccount,null,(long)(amount*100),3);
        transactionService.submitTransaction(transaction);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam String fromAccount,@RequestParam String toAccount,@RequestParam double amount) {
        String id = UUID.randomUUID().toString();
        Transaction t = new Transaction(id,TransactionType.TRANSFER,fromAccount,toAccount,(long)(amount*100),3);
        transactionService.submitTransaction(t);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/simulate")
    public ResponseEntity<String> simulate(@RequestParam(defaultValue = "200") int count) {
        new Thread(() -> {
            TransactionProducer producer = new TransactionProducer(transactionService);
            producer.simulate(count);
        }).start();
        return ResponseEntity.accepted().body("Simulation started");
    }
}
