package com.hs.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger能够进行原子性操作，但是操作过程中。值可能已经多次发生变化，但无法察觉。
 */
public class CASD2 {

    private static AtomicInteger atomicNum = new AtomicInteger(100);

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            System.out.println(atomicNum.compareAndSet(100,120));
        });
        t1.start();

        Thread t2 = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicNum.compareAndSet(120,100));
        });

        t2.start();

        Thread t3 = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicNum.compareAndSet(100,120));
        });
        t3.start();
    }
}
