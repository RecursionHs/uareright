package com.hs.Thread;

public class MyThread extends Thread{
    private  int count  = 5;
    @Override
    public synchronized void run() {
        count--;
        System.out.println(MyThread.currentThread().getName() + "count: " + count);
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread thread1 = new Thread(myThread,"gaga1");
        Thread thread2 = new Thread(myThread,"gaga2");
        Thread thread3 = new Thread(myThread,"gaga3");
        Thread thread4 = new Thread(myThread,"gaga4");
        Thread thread5 = new Thread(myThread,"gaga5");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }
}
