package com.shane.example.core;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Shane
 * @version 1.0
 * @date 2023-08-21 22:51
 */
public class LogReplicationScheduler {

    private final ScheduledExecutorService executor;
    private final ScheduledFuture<?> scheduledFuture;

    public LogReplicationScheduler(Runnable task, int logReplicationDelay, int logReplicationInterval) {
        // 调度线程池
        executor = Executors.newSingleThreadScheduledExecutor();
        scheduledFuture = executor.scheduleWithFixedDelay(task, logReplicationDelay, logReplicationInterval, TimeUnit.MILLISECONDS);
    }

    public void cancel() {
        scheduledFuture.cancel(false);
    }

    @Override
    public String toString() {
        return "LogReplicationScheduler{" +
                "delay=" + scheduledFuture.getDelay(TimeUnit.MILLISECONDS) + '}';
    }
}
