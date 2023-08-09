package com.shane.example.core;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 随机或者可配置的时延
 * 重置计时器
 *
 * @author: shane
 * @date: 2023-08-08 10:20:27
 * @version: 1.0
 */
public class ElectionTimeout {

    private static final int MIN_ELECTION_PERIOD = 150;
    private static final int MAX_ELECTION_PERIOD = 300;
    private final Random random = new Random();
    private ScheduledExecutorService executor;
    private ScheduledFuture<?> scheduledFuture;


    public ElectionTimeout() {
        // 调度线程池
        executor = Executors.newSingleThreadScheduledExecutor();
    }

    public ElectionTimeout submit(Runnable task) {
        int electionPeriod = MIN_ELECTION_PERIOD + random.nextInt(MAX_ELECTION_PERIOD - MIN_ELECTION_PERIOD);
        scheduledFuture = executor.schedule(task, electionPeriod, TimeUnit.MILLISECONDS);
        return this;
    }

    public boolean cancel(){
        return scheduledFuture.cancel(false);
    }
}
