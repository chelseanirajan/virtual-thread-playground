package org.example.sec05.externalservice.aggregaor;

import org.example.sec04.externalservice.Client;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

public class AggregatorService {
    private final ExecutorService executorService;
    public AggregatorService(ExecutorService executorService){
        this.executorService = executorService;
    }

    public ProductDto getProductDto(int id) throws ExecutionException, InterruptedException{
        var product = CompletableFuture.supplyAsync(() -> Client.getProduct(id), executorService).exceptionally(ex -> "product-not-found.");
        var rating = CompletableFuture.supplyAsync(() -> Client.getRating(id), executorService).exceptionally(ex -> -1);
        return new ProductDto(id, product.get(), rating.get());
    }
}
