package com.shane.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Source;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: shane
 * @date: 2023-08-24 10:19:40
 * @version: 1.0
 */
public class Mian {

    private static final ExecutorService executorService = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new SynchronousQueue<>());

    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING = -1 << COUNT_BITS;
    private static final int SHUTDOWN = 0 << COUNT_BITS;
    private static final int STOP = 1 << COUNT_BITS;
    private static final int TIDYING = 2 << COUNT_BITS;
    private static final int TERMINATED = 3 << COUNT_BITS;

    // Packing and unpacking ctl
    private static int runStateOf(int c) {
        return c & ~CAPACITY;
    }

    private static int workerCountOf(int c) {
        return c & CAPACITY;
    }

    private static int ctlOf(int rs, int wc) {
        return rs | wc;
    }

    public static void main(String[] args) {
//        System.out.println(RUNNING);
//        System.out.println(SHUTDOWN);
//        System.out.println(STOP);
//        System.out.println(TIDYING);
//        System.out.println(TERMINATED);
//        System.out.println(~CAPACITY);


//        System.out.println(Integer.toBinaryString(ctlOf(RUNNING, 0)));
        executorService.execute(() -> System.out.println(Thread.currentThread().getName()));

    }
}
