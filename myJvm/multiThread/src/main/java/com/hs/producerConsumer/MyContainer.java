package com.hs.producerConsumer;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 自定义容器实现生产者消费者
 */
public class MyContainer<T> {

    final private LinkedList<T> lists= new LinkedList<>();

    //容量大小
    final private int MAX = 10;

    private int count = 0;


    //生产者方法

    public synchronized void put(T t){
        while(lists.size() == MAX){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(t);
        ++ count;
        this.notifyAll();
    }

    //消费者方法
    public synchronized T get(){
        T t = null;
        while(lists.size() == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = lists.removeFirst();
        count --;
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        MyContainer<String> c = new MyContainer<>();
        for (int i = 0; i < 10; i++) {
           new Thread(()->{
               for (int j = 0; j < 5; j++) {
                   System.out.println("c --> " + c.get());
               }
           },"c:"+i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 25; j++) {
                    c.put(Thread.currentThread().getName() + " " + j);
                    System.out.println("p : " + Thread.currentThread().getName() + " " + j);
                }
            },"p:"+i).start();
        }
    }
}
