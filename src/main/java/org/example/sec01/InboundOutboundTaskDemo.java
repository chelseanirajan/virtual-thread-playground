package org.example.sec01;

import java.util.concurrent.CountDownLatch;

public class InboundOutboundTaskDemo {
    private static final int MAX_PLATFORM = 10;
    public static final int MAX_VIRTUAL = 10000;

    public static void main(String[] args) throws InterruptedException {
        virtualThreadDemo();
    }
    private static void platformThreadDemo1(){
        for(int i = 0;i<MAX_PLATFORM;i++){
            int finalI = i;
            Thread thread = new Thread(() -> Task.ioIntensive(finalI));
            thread.start();
        }
    }

    private static void platformThreadDemo2(){
        var builder = Thread.ofPlatform().name("vins", 1);
        for(int i = 0;i<MAX_PLATFORM;i++){
            int finalI = i;
            Thread thread = builder.unstarted(() -> Task.ioIntensive(finalI));
            thread.start();
        }
    }

    private static void platformThreadDemo3() throws InterruptedException {
        var latch = new CountDownLatch(MAX_PLATFORM);
        var builder = Thread.ofPlatform().daemon().name("daemon", 1);
        for(int i = 0;i<MAX_PLATFORM;i++){
            int finalI = i;
            Thread thread = builder.unstarted(() -> {
                        Task.ioIntensive(finalI);
                        latch.countDown();
                    });

            thread.start();
        }
        latch.await();
    }

    private static void virtualThreadDemo() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(MAX_VIRTUAL);
        Thread.Builder.OfVirtual virtual = Thread.ofVirtual().name("virtual", 1);
        for(int i = 0; i< MAX_VIRTUAL; i++){
            int finalI = i;
            Thread thread = virtual.unstarted(() ->
            {
                Task.ioIntensive(finalI);
                countDownLatch.countDown();
            });
            thread.start();
        }
        countDownLatch.await();
    }
}
