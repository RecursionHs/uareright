package com.thre;

import java.util.concurrent.*;

/**
 * @author : sk
 * @version V1.0
 * @Project: myJvm
 * @Package com.thre
 * @Description: TODO
 * @date Date : 2020年05月21日 下午11:34
 */
public class CallableThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        long l = System.currentTimeMillis();
        String s = String.valueOf(l);
        TimeUnit.SECONDS.sleep(2);
        return s;
    }

    public static void main(String[] args) throws ExecutionException,InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CallableThread task = new CallableThread();
        Future<String> future = pool.submit(task);
        System.out.println(future.get());
        pool.shutdown();


    }
}
