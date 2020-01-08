package com.hs.thread;

import java.util.Random;

public class ExtendsThreadImpl extends Thread{

    private String flag;
    public ExtendsThreadImpl(String flag){
            this.flag = flag;
    }

    @Override
    public void run() {
        String  tname = Thread.currentThread().getName();
        System.out.println(tname + "线程run被调用了!!");
        Random random = new Random();
        for (int i = 0;i < 20; i++){
            try {
                Thread.sleep(random.nextInt(10)*500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tname + "..." +flag);
        }
    }

    public static void main(String[] args) {
        ExtendsThreadImpl runThread1 = new ExtendsThreadImpl("a");
        ExtendsThreadImpl runThread2 = new ExtendsThreadImpl("b");
        runThread1.start();
        runThread2.start();
    }
}
