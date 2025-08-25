package org.example.sec04.concurrencylimit;

import org.example.sec04.externalservice.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrencyLimitWithSemaphore {
    private static final Logger log = LoggerFactory.getLogger(ConcurrencyLimitWithSemaphore.class);

    public static void main(String[] args) throws Exception {
        var factory = Thread.ofVirtual().name("nir", 1).factory();
        var limiter = new ConcurrencyLimiter(Executors.newThreadPerTaskExecutor(factory), 3);
        execute(limiter, 20);
//        execute(Executors.newFixedThreadPool(3), 20);
    }

    private static void execute(ConcurrencyLimiter concurrencyLimiter, int taskCount) throws Exception {
        try(concurrencyLimiter){
            for(int i = 1;i<=taskCount;i++){
                int j = i;
                concurrencyLimiter.submit(() -> printProductionInfo(j));
            }
            log.info("submitted");
        }
    }

    private static String printProductionInfo(int id){
        String product = Client.getProduct(id);
        log.info("{} => {}",id, product);
        return product;

    }
}
