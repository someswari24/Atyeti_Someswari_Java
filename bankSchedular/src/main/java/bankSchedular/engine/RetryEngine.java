package bankSchedular.engine;

import bankSchedular.model.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class RetryEngine {
    private final ScheduledExecutorService executorService= Executors.newScheduledThreadPool(2);
    private final BankEngine bankEngine;

    public void scheduleRetry(Transaction transaction,int attempt,Runnable submit){
        if (attempt>5) return;
        long delay=Math.min(1000L * (1L << (attempt-1)),10000L);
        executorService.schedule(submit,delay, TimeUnit.MILLISECONDS);
    }
}
