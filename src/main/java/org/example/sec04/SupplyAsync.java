package org.example.sec04;

import org.example.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class SupplyAsync {

    private static final Logger log = LoggerFactory.getLogger(RunAsAsync.class);

    public static void main(String[] args) {
        log.info("method starts");

        log.info("method ends");
    }
    private static CompletableFuture<String> slowTask(){
        log.info("method starts");
        var cf = CompletableFuture.supplyAsync(() -> {
            CommonUtils.sleep(Duration.ofSeconds(1));
            return "hi";
        }, Executors.newVirtualThreadPerTaskExecutor());
        log.info("method ends");
        return cf;
    }
}
