package com.hs.reference.threadlocal;

import com.hs.Ka;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestForThreadL
 * @Description threadLocal，你以为key为线程对象存入threadLocal对象？
 * @Author hsir
 * @Date 2020/6/21 下午1:35
 * @Version 1.0
 */
public class TestForThreadL {
    public static void main(String[] args) {
        ThreadLocal<Ka> tl = new ThreadLocal<>();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A ->" + tl.get());
        },"A").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Ka());
            System.out.println("B ->" + tl.get());
            tl.remove();
        },"B").start();

    }
    /**输出
     * B ->com.hs.Ka@1975a0ed
     * A ->null
     */
}
