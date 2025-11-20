package bankSchedular.model;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    @Getter
    private final String accountId;
    private final AtomicLong balanceCents=new AtomicLong(0);
    @Getter
    private final ReentrantLock lock=new ReentrantLock(true);

    public Account(String accountId,long initialCents) {
        this.accountId = accountId;
        this.balanceCents.set(initialCents);
    }

    public long getBalanceCents() {
        return balanceCents.get();
    }

    public long addAndGet(long delta) {
        return balanceCents.addAndGet(delta);
    }
}
