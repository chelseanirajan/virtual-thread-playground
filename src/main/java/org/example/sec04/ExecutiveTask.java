package org.example.sec04;

import org.example.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutiveTask {
    private static final Logger log = LoggerFactory.getLogger(ExecutiveTask.class);

    public static void main(String[] args) {
//        execute(Executors.newSingleThreadExecutor(), 2);
//        execute(Executors.newFixedThreadPool(200), 200);
//        execute(Executors.newCachedThreadPool(), 200);
        execute(Executors.newVirtualThreadPerTaskExecutor(), 200);
    }

    private static void execute(ExecutorService executor, int taskNumber) {
        try (executor) {
            for (int i = 0; i < taskNumber; i++) {
                int j = i;
                executor.submit(() -> task(j));
            }
        }
    }

    private static void task(int i) {
        log.info("Started Thread {}. Thread processor {}.", i, Thread.currentThread());
        CommonUtils.sleep(Duration.ofSeconds(1));
        log.info("Ended Thread {}. Thread processor {}.", i, Thread.currentThread());
    }

}
