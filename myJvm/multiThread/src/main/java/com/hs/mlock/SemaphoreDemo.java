package com.hs.mlock;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 模拟停车场
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        //五个车位
        Semaphore semaphore = new Semaphore(5);
        //10辆车
        Thread[] td = new Thread[10];
        for (int i = 0; i < td.length; i++) {
            td[i] = new Thread(()->{

                try {
                    //获取许可证
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "进入停车场");
                    //业务逻辑,使用资源
                    int stime = new Random().nextInt(10);
                    TimeUnit.SECONDS.sleep(stime);

                    System.out.println(Thread.currentThread().getName() + "进入停车场停留了" + stime + "s；"  );
                    //出停车场,释放资源
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + "出去了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"车:" + i);
            td[i].start();
        }

    }
}
