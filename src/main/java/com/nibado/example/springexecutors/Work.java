package com.nibado.example.springexecutors;

import java.util.concurrent.CompletableFuture;

public class Work {
    private final CompletableFuture<Result> future;

    String databaseResult;
    String clientResult;

    public Work(CompletableFuture<Result> future) {
        this.future = future;
    }

    public void complete() {
        String message = databaseResult + "\n" + clientResult;

        future.complete(new Result(databaseResult, clientResult));
    }
}
