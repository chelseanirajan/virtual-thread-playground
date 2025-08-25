package org.example.sec05.externalservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class GetProducts {
    private static final Logger log = LoggerFactory.getLogger(GetProducts.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try(var executor = Executors.newVirtualThreadPerTaskExecutor()){
            var product1 = CompletableFuture.supplyAsync(() -> Client.getProduct(1), executor);
            var product2 = CompletableFuture.supplyAsync(() -> Client.getProduct(2), executor);
            var product3 = CompletableFuture.supplyAsync(() -> Client.getProduct(3), executor);

            log.info("product-1: {}", product1.get());
            log.info("product-2: {}", product2.get());
            log.info("product-3: {}", product3.get());
        }
    }
}
