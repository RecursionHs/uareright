package com.hs.mlock;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 模拟8个赛跑选手
 * 允许一组线程全部等待彼此达到共同屏障点的同步辅助。 循环阻塞在涉及固定大小的线程方的程序中很有用，
 * 这些线程必须偶尔等待彼此。
 * 屏障被称为循环 ，因为它可以在等待的线程被释放之后重新使用。
 */
public class CyclieBarriesDemo {

    public static void main(String[] args) {
        Thread[] td = new Thread[8];
        CyclicBarrier barrier = new CyclicBarrier(8);
        for (int i = 0; i < td.length; i++) {
            td[i] = new Thread(()->{
                //模拟准备动作
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                    System.out.println("选手" + Thread.currentThread().getName() + "准备好了...");
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + "开始跑...");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            },"选手" + i);
            td[i].start();
        }
    }
}
