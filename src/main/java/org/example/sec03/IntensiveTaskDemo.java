package org.example.sec03;


import java.util.concurrent.CountDownLatch;

public class IntensiveTaskDemo {
    private static final int MAX_COUNT = 2* Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws InterruptedException {
//        System.out.println(CommonUtils.timer(() -> Task.fibonacci(34)));
        demo(Thread.ofPlatform());
    }

    private static void demo(Thread.Builder builder) throws InterruptedException {
        var countDown = new CountDownLatch(1);
        for(int i = 1;i<= MAX_COUNT; i++){
//            int j = i;
            builder.start(() -> {
                Task.intensiveTask(45);
                countDown.countDown();
            });
        }
            countDown.await();

    }
}
