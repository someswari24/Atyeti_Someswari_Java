package bankSchedular.service;

import bankSchedular.model.Account;
import bankSchedular.repository.AccountRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    @PostConstruct
    private void init() {
        for (int i = 0; i <= 10; i++) {
            accountRepository.create("A"+i,10000L * i * 100);
        }
    }

    public Account get(String id) {
        return accountRepository.find(id);
    }

    public List<String> allIds() {
        return accountRepository.all().stream().map((Account::getAccountId)).collect(Collectors.toList());
    }
    public String generateBulkAccounts(int count) {
        for (int i = 1; i <= count; i++) {
            String accountId = "ACC" + i;
            long initialBalance = 10000;

            Account account = new Account(accountId, initialBalance);
            accountRepository.save(account);
        }
        return count + " accounts created successfully.";
    }

}

