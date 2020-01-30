package com.hs.mlock;

/**
 * 可重入性
 */
public class Demo1 {
    private MyLock mlock = new MyLock();

     public  void a(){
         mlock.lock();
         System.out.println("a");
         b();
         mlock.unlock();
     }
     public  void b(){
         mlock.lock();
         System.out.println("b");
         mlock.unlock();
     }

    public static void main(String[] args) {
        Demo1 d1 = new Demo1();
        new Thread(()->{
            d1.a();
        }).start();
    }
}
