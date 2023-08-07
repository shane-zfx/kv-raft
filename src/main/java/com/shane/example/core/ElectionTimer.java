package com.shane.example.core;

import com.sun.org.apache.bcel.internal.generic.NEW;

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
public class ElectionTimer {

    private static final int MIN_ELECTION_PERIOD = 150;
    private static final int MAX_ELECTION_PERIOD = 300;
    private final Random random = new Random();
    private ScheduledExecutorService executor;
    private ScheduledFuture<?> electionTimeout;


    public ElectionTimer() {
        executor = Executors.newSingleThreadScheduledExecutor();
    }

    public void start() {
        // 开启新一轮计时器
        int randomElectionPeriod = MIN_ELECTION_PERIOD + random.nextInt(MAX_ELECTION_PERIOD - MIN_ELECTION_PERIOD);
        // 取消之前提交的任务
        if (electionTimeout != null) {
            electionTimeout.cancel(false);
        }
        electionTimeout = executor.schedule(this::handleElectionTimeout, randomElectionPeriod, TimeUnit.MILLISECONDS);
    }

    public void handleElectionTimeout() {
        // 选举计时器，超时之后的处理逻辑
        // 1. 角色升级为候选
        // 2. 任期 + 1
        // 3. 给自己投票 （默认）
        // 4. 广播拉票请求
        // 5.

    }
}
