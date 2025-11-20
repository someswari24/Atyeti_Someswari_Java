package bankSchedular.repository;

import bankSchedular.model.Account;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccountRepository {
    private final ConcurrentHashMap<String, Account>accounts=new ConcurrentHashMap<>();

    public Account create(String accountId,long initialCents){
        Account account=new Account(accountId,initialCents);
        accounts.put(accountId,account);
        return account;
    }

    public Account find(String accountId){
        return accounts.get(accountId);
    }

    public Collection<Account>all(){
        return accounts.values();
    }

}
