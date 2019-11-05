package com.nibado.example.springexecutors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class Controller {
    private final ServiceA serviceA;

    public Controller(ServiceA serviceA) {
        this.serviceA = serviceA;
    }

    @GetMapping("/ticket")
    public CompletableFuture<Result> getResult() {
        return serviceA.getTicket();
    }

}
