package bankSchedular.controller;

import bankSchedular.model.Account;
import bankSchedular.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountId) {
        Account account = accountService.get(accountId);

        if (account == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(account);
    }

    @GetMapping
    public List<String> all() {
        return accountService.allIds();
    }

    @PostMapping("/generate")
    public String generate(@RequestParam int count) {
        return accountService.generateBulkAccounts(count);
    }
}
