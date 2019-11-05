package com.nibado.example.springexecutors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ServiceA {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceA.class);

    private final ServiceB serviceB;

    public ServiceA(ServiceB serviceB) {
        this.serviceB = serviceB;
    }

    public CompletableFuture<Result> getTicket() {
        LOG.info("getTicket()");

        CompletableFuture<Result> future = new CompletableFuture<>();

        serviceB.submit(new Work(future));

        return future;
    }
}
