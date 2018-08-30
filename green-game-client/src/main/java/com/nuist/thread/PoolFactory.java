package com.nuist.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PoolFactory {

    private static ExecutorService executorService;

    public static ExecutorService getThreadPool() {
        if (executorService == null) {
            executorService = Executors.newFixedThreadPool(10);
        }
        return executorService;
    }
}
