package org.example.sec03;

import org.example.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Task {
    private static final Logger log = LoggerFactory.getLogger(Task.class);

    public static void intensiveTask(int i){
        log.info("staring cpu task. Thread info {}", Thread.currentThread());
        long timeTaken = CommonUtils.timer(() -> fibonacci(i));
        log.info("ending cpu task. Time taken: {} ms", timeTaken);

    }
    public static int fibonacci(int n){
        if(n<2){
            return n;
        }
        return fibonacci(n-1) + fibonacci(n-2);
    }
}
