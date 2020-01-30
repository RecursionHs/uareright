package com.hs.threadPool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 单线程池
 */
public class SingleThreadExecutorDemo {

    public static void main(String[] args) {
        ThreadFactory threadFactory =
                new ThreadFactoryBuilder().setNameFormat("singleThread-%d").build();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 10; i++) {
            MyTask task = new MyTask(i);
            pool.execute(task);
        }
        System.out.println("========关闭线程指令========");
        pool.shutdown();


    }
}
