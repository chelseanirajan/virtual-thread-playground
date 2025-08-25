package org.example.sec04;

import org.example.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class RunAsAsync {
    private static final Logger log = LoggerFactory.getLogger(RunAsAsync.class);

    public static void main(String[] args) {
        log.info("method starts");

        log.info("method ends");
    }
    private static CompletableFuture<String> slowTask(){
        log.info("method starts");
        var cf = CompletableFuture.runAsync(() -> {
            CommonUtils.sleep(Duration.ofSeconds(1));

//            throw new RuntimeException();
        });
        log.info("method ends");
        return null;
    }
}
