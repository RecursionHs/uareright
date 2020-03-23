package com.hs.threadPool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * cache线程池
 */
public class CachedThreadPoolDemo {

    public static void main(String[] args) {
        //虚拟机处理器数,8
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(processors);
        ThreadFactory threadFactory =
                new ThreadFactoryBuilder().setNameFormat("CacheThread-%d").build();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(processors*2, 200, 60L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 10; i++) {
            MyTask task = new MyTask(i);
            pool.execute(task);
        }
        System.out.println("========关闭线程指令=======");
        pool.shutdown();
    }
}
