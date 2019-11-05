package com.nibado.example.springexecutors;

public class Result {
    private final String database;
    private final String client;

    public Result(String database, String client) {
        this.database = database;
        this.client = client;
    }

    public String getDatabase() {
        return database;
    }

    public String getClient() {
        return client;
    }
}
