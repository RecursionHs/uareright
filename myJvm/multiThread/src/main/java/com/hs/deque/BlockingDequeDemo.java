package com.hs.deque;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 */
public class BlockingDequeDemo {

    private static LinkedBlockingDeque<String> lbd = new LinkedBlockingDeque(3);

    public static void main(String[] args) {
        //1个线程往里面存值，主线程取值
        Thread mth = new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    //put方式是队列有空余的位置再插值，是阻塞的，而add是不行的。
                    lbd.put("值:" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("存值线程: size " + lbd.size());
            }
        });
        mth.start();

        //主线程取值
        while(true){
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(lbd.size() > 0) {
                    System.out.println("取值: " + lbd.poll() + "剩余size" + lbd.size()
                    + "[存值线程: " + mth.getName() + "线程状态 :" + mth.getState() + "]");
            }else{
                break;
            }

        }
    }
}
