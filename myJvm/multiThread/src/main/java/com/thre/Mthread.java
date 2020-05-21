package com.thre;

/**
 * @author : sk
 * @version V1.0
 * @Project: myJvm
 * @Package com.thre
 * @Description: TODO
 * @date Date : 2020年05月21日 下午11:29
 */
public class Mthread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread() + " thread start...");
    }

    public static void main(String[] args) {
        Mthread mthread = new Mthread();
        Thread newthread = new Thread(mthread, "newthread");
        newthread.start();
    }
}
