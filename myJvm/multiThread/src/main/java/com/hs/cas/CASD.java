package com.hs.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CASD {

    private static volatile int mnum = 0;

    private static AtomicInteger atomicNum = new AtomicInteger(0);
    public static void incrementS1(){
        mnum++;
    }
    public static void incrementS2(){
        atomicNum.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException{
        Thread[] td1 = new Thread[20];
        for (int i = 0; i < 20; i++) {
            td1[i] = new Thread(()->{
                CASD.incrementS1();
            });
            td1[i].start();
            td1[i].join();
        }

        System.out.println(CASD.mnum);
        Thread[] td2 = new Thread[20];
        for (int i = 0; i < 20; i++) {
            td2[i] = new Thread(()->{
                CASD.incrementS2();
            });
            td2[i].start();
            td2[i].join();
        }
        System.out.println(CASD.atomicNum.get());
    }

}
