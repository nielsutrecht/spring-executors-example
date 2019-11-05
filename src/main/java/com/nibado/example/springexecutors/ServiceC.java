package com.nibado.example.springexecutors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ServiceC {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceC.class);

    private static final int NUMBER_OF_THREADS = 10;
    private static final int QUEUE_SIZE = 20;

    private final ExecutorService workers = new ThreadPoolExecutor(NUMBER_OF_THREADS, NUMBER_OF_THREADS, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(QUEUE_SIZE));
    private final AtomicInteger counter = new AtomicInteger();

    public void submit(Work work) {
        workers.submit(() -> process(work));
        LOG.info("Work submitted");
    }

    private void process(Work work) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e){}

        work.clientResult = Thread.currentThread().getName() + " completed " + counter.getAndIncrement();

        work.complete();

        LOG.info("Work completed");
    }
}
