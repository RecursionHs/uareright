package com.hs.threadPool;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MyTask implements Runnable{
    private int taskId;

    public MyTask(int taskId){
        this.taskId = taskId;
    }

    public void run() {
        try {
            System.out.println("线程:" +Thread.currentThread().getName()+ "开始执行提交过来的任务: [" + taskId + "]");
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
