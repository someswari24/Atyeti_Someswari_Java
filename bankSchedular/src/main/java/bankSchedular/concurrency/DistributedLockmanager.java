package bankSchedular.concurrency;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class DistributedLockmanager {
    @AllArgsConstructor
    private static class Lease{
        final String owner;
        final long expiry;
    }

    private final ConcurrentHashMap<String,Lease>leases=new ConcurrentHashMap<>();


    public boolean acquire(String key,long time){
        long now=System.currentTimeMillis();
        Lease newLease=new Lease(Thread.currentThread().getName(),now+time);
        Lease previous=leases.putIfAbsent(key,newLease);
        if(previous==null) return true;
        if (previous.expiry<now){
            return leases.replace(key,previous,newLease);
        }
        return false;
    }

    public void release(String key){
        leases.remove(key);
    }


}
