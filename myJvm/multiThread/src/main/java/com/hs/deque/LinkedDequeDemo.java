package com.hs.deque;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;

public class LinkedDequeDemo {

    private static ConcurrentLinkedDeque cld = new ConcurrentLinkedDeque();

    public static void main(String[] args) {
        //100个线程做插入操作
        Thread[] td1 = new Thread[100];
        for (int i = 0; i < td1.length; i++) {
            td1[i] = new Thread(()->{
                for (int j = 0; j < 100; j++) {
                    cld.add("线程:" + Thread.currentThread().getName() + "【" + j + "]");
                }
            },"td1[" + i + "]");
            td1[i].start();
            try {
                td1[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("td1线程池存入:" + cld.size());

        Iterator mit = cld.iterator();
        while(mit.hasNext()){
            //迭代可以移除值
            cld.remove(mit.next());
        }
        System.out.println("取值后size:" + cld.size());
    }
    }
