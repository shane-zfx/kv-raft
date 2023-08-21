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
    private final ScheduledExecutorService executor;
    private final ScheduledFuture<?> scheduledFuture;


    public ElectionTimeout(Runnable task) {
        this(task, MIN_ELECTION_PERIOD, MAX_ELECTION_PERIOD);
    }

    public ElectionTimeout(Runnable task, int minElectionPeriod, int maxElectionPeriod) {
        // 调度线程池
        executor = Executors.newSingleThreadScheduledExecutor();
        // 选举及时器提交超时任务，开始计时
        int electionPeriod = minElectionPeriod + new Random().nextInt(maxElectionPeriod - minElectionPeriod);
        scheduledFuture = executor.schedule(task, electionPeriod, TimeUnit.MILLISECONDS);
    }

    public void cancel() {
        scheduledFuture.cancel(false);
    }

    @Override
    public String toString() {
        if (this.scheduledFuture.isCancelled()) {
            return "ElectionTimeout{ state = cancelled }";
        }
        if (this.scheduledFuture.isDone()) {
            return "ElectionTimeout{ state = done }";
        }
        return "ElectionTimeout{ delay = " + scheduledFuture.getDelay(TimeUnit.MILLISECONDS) + "ms } ";
    }
}
