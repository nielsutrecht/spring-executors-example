package com.nibado.example.springexecutors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ServiceB {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceB.class);

    private static final int NUMBER_OF_THREADS = 2;
    private static final int QUEUE_SIZE = 20;

    private final ExecutorService workers = new ThreadPoolExecutor(NUMBER_OF_THREADS, NUMBER_OF_THREADS, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(QUEUE_SIZE));
    private final AtomicInteger counter = new AtomicInteger();
    private final ServiceC serviceC;

    public ServiceB(ServiceC serviceC) {
        this.serviceC = serviceC;
    }

    public void submit(Work work) {
        workers.submit(() -> process(work));
        LOG.info("Work submitted");
    }

    private void process(Work work) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e){}

        work.databaseResult = Thread.currentThread().getName() + " completed " + counter.getAndIncrement();

        serviceC.submit(work);
    }
}
