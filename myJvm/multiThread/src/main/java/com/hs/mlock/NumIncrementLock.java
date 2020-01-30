package com.hs.mlock;

import java.util.concurrent.TimeUnit;

public class NumIncrementLock {

    private MyLock mlock = new MyLock();

    private int mnum = 0;

    private int addNum(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mlock.lock();
        try {
            return mnum++;
        } finally {
            mlock.unlock();
        }
    }

    public static void main(String[] args) {
        NumIncrementLock ni = new NumIncrementLock();
        Thread[] td = new Thread[20];
        for (int i = 0; i < 20; i++) {
            td[i] = new Thread(()->{
                System.out.println(ni.addNum());
            });
            td[i].start();
        }

    }
}
