package com.shane.example.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: shane
 * @date: 2023-08-24 10:19:40
 * @version: 1.0
 */
public class Mian {
    static Logger LOGGER = LoggerFactory.getLogger(Mian.class);

    private static final ExecutorService executorService = Executors.newCachedThreadPool((r) -> {
        Thread thread = new Thread(r);
        thread.setUncaughtExceptionHandler((t, e) -> {
            LOGGER.error("error", e);
        });
        return thread;
    });

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executorService.execute(() -> {
                System.out.println(finalI);
            });
        }
        //

        System.out.println("主线程进度");
        executorService.shutdown();
    }
}
