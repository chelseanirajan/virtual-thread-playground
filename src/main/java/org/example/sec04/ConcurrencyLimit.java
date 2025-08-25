package org.example.sec04;

import org.example.sec04.externalservice.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrencyLimit {
    private static final Logger log = LoggerFactory.getLogger(ConcurrencyLimit.class);

    public static void main(String[] args) {
        execute(Executors.newFixedThreadPool(3), 20);
    }

    private static void execute(ExecutorService executorService, int taskCount){
        try(executorService){
            for(int i = 1;i<=taskCount;i++){
                int j = i;
                executorService.submit(() -> printProductionInfo(j));
            }
            log.info("submitted");
        }
    }

    private static void printProductionInfo(int id){
        log.info("{} => {}",id, Client.getProduct(id));
    }
}
