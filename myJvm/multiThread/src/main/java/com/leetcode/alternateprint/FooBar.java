package com.leetcode.alternateprint;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @Author hsir
 * @Date 2019-12-9
 * @Version 1.0
 **/
public class FooBar {
    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    CyclicBarrier cb = new CyclicBarrier(2);
    volatile boolean fin = true;

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
                if(fin){
                    printFoo.run();
                    try {
                        fin = false;
                        cb.await();
                        cb.reset();
                    } catch (BrokenBarrierException e) {
                    }
                }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
                    if(!fin){
                        printBar.run();
                        try {
                            fin = true;
                            cb.await();
                        } catch (BrokenBarrierException e) {
                        }
                    }


        }
    }

    public static void main(String[] args) {
        FooBar fooBar = new FooBar(1);

        /*ThreadFactory tname = new ThreadFactoryBuilder().setNameFormat("t-pool-%d").build();
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 2,0L,TimeUnit.MICROSECONDS, new LinkedBlockingQueue<Runnable>(1024),
                tname, new ThreadPoolExecutor.AbortPolicy());*/

        Runnable runnable1 = ()->{
            System.out.print("foo");

        };
        Runnable runnable2 = ()->{
            System.out.println("bar");
        };

        /*try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        new Thread(()->{
            try {
                fooBar.foo(runnable1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"foo-thread").start();

        new Thread(()->{
            try {
                fooBar.bar(runnable2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"bar-thread").start();



    }
}
