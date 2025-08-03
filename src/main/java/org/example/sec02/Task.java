package org.example.sec02;

import org.example.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Task {
    private static final Logger log = LoggerFactory.getLogger(Task.class);

    public static void execute(int i){
        log.info("staring task {}", i);
        try{
            method1(i);

        }catch (Exception ex){
            log.error("error for {}", i, ex);
        }
        log.info("ending task {}", i);

    }
    private static void method1(int i){
        CommonUtils.sleep(Duration.ofMillis(300));
        try{
            method2(i);
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }

    }
    private static void method2(int i){
        CommonUtils.sleep(Duration.ofMillis(100));
        method3(i);
    }

    private static void method3(int i){
        CommonUtils.sleep(Duration.ofMillis(500));
        if(i == 4){
            throw new IllegalStateException("i can not be 4");
        }
    }
}
