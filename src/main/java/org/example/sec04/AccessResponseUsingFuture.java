package org.example.sec04;

import org.example.sec04.externalservice.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AccessResponseUsingFuture {
    private static final Logger log = LoggerFactory.getLogger(AccessResponseUsingFuture.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try(var executor = Executors.newVirtualThreadPerTaskExecutor()){
            Future<String> product1 = executor.submit(() -> Client.getProduct(1));
            Future<String> product2 = executor.submit(() -> Client.getProduct(2));
            Future<String> product3 = executor.submit(() -> Client.getProduct(3));
            log.info("Product -1 {}",product1.get());
            log.info("Product -2 {}",product2.get());
            log.info("Product -3 {}",product3.get());
        }
    }
}
