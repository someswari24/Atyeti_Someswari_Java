package bankSchedular.fraud;

import bankSchedular.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class FraudDetector {
    public final Map<String , AtomicLong>recentCounts=new ConcurrentHashMap<>();

    public boolean detectFraud(Transaction transaction){
        if (transaction.getAmountCents()>500000){
            if (Math.random()<0.1) return true;
        }
        if (transaction.getFromAccount()!=null){
            recentCounts.computeIfAbsent(transaction.getFromAccount(),k->new AtomicLong()).incrementAndGet();
            if (recentCounts.get(transaction.getFromAccount()).get()>8) return true;
        }
        return false;
    }
}
