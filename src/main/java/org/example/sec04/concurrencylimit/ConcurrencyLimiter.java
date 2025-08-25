package org.example.sec04.concurrencylimit;

import org.example.sec04.ConcurrencyLimit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.*;


public class ConcurrencyLimiter implements AutoCloseable {
    private static final Logger log = LoggerFactory.getLogger(ConcurrencyLimiter.class);
    private final ExecutorService executorService;
    private final Semaphore semaphore;
    private final Queue<Callable<?>> queue;

    public ConcurrencyLimiter(ExecutorService executorService, int limit){
        this.executorService = executorService;
        this.semaphore = new Semaphore(limit);
        this.queue = new ConcurrentLinkedQueue<>();
    }

    public <T> Future<T> submit(Callable<T> callable){
        this.queue.add((callable));
        return executorService.submit(() ->  taskExecute());

    }

    private <T> T taskExecute(){
        try{
            semaphore.acquire();
            return (T)this.queue.poll().call();
        }catch(Exception e){
            log.error("error", e);
        }finally {
            semaphore.release();
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        this.executorService.close();
    }
}
