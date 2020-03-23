package com.hs.threadPool;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 固定大小的线程池
 */
public class FixedThreadPoolDemo {

    public static void main(String[] args) {
        //可以通过获取可用于JAVA虚拟机的处理器数量设置最小值
        //System.out.println(Runtime.getRuntime().availableProcessors() * 2);

        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("My-Pool-%d").build();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 10, 10L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),threadFactory,new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 10; i++) {
            MyTask task = new MyTask(i);
            pool.execute(task);
        }
        System.out.println("=======关闭线程指令=======");
        pool.shutdown();
    }
    
}
