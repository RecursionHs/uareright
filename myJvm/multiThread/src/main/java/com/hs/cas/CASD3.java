package com.hs.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * AtomicStampedReference是用来解决ABA问题的。它是使用版本号的方式来解决。
 * 如下，版本号不一致，就会导致数据修改失败。
 */
public class CASD3 {

        private static AtomicStampedReference asf = new AtomicStampedReference(100,1);


        public static void main(String[] args) {

            Thread t1 = new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(asf.compareAndSet(100,120,asf.getStamp(),asf.getStamp() + 1));
                System.out.println(asf.compareAndSet(120,100,asf.getStamp(),asf.getStamp() + 1));

            });

            Thread t2 = new Thread(()->{
                int myTamp = asf.getStamp();
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2" + asf.compareAndSet( 100,120,myTamp,asf.getStamp() + 1));

            });

            t1.start();
            t2.start();
        }
}
