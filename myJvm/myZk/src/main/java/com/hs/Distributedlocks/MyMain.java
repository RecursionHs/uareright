package com.hs.Distributedlocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyMain {

    public static void main(String[] args) {
        Thread t1 = new Thread(new UserThread(),"user1");
        Thread t2 = new Thread(new UserThread(),"user2");
        t1.start();
        t2.start();
    }
    //static Lock lock = new ReentrantLock();
    //自定义的zk锁
    static ZkLock lock = new ZkLock();

    static class UserThread implements Runnable{
        @Override
        public void run() {
            new Order().createOrder();
            lock.lock();
            boolean result = new Stock().reduceStock();
            lock.unlock();
            if(result){
                System.out.println(Thread.currentThread().getName() +"减库存成功!");
                new Pay().pay();
            }else{
                System.out.println(Thread.currentThread().getName() +"减库存失败!");
            }
        }
    }
}
