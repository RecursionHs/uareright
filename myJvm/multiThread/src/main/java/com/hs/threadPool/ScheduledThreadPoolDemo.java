package com.hs.threadPool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolDemo {

    public static void main(String[] args) {
        ThreadFactory threadFactory =
                new ThreadFactoryBuilder().setNameFormat("ScheduledThread-%d").build();
        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(5, threadFactory);

        for (int i = 0; i < 10; i++) {
            MyTask task = new MyTask(i);
            pool.schedule(task,2L, TimeUnit.SECONDS);
        }
        System.out.println("=========关闭线程指令========");
        pool.shutdown();
    }
}
