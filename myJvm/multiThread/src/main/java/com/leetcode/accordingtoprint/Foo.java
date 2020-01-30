package com.leetcode.accordingtoprint;

/**
 * @Author hsir
 * @Date 2019-12-6
 * @Version 1.0
 **/
public class Foo {

     public Foo(){}




     private int flag = 0;

     private Object olock = new Object();

     public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.

         synchronized (olock){
             while(flag != 0){
                 olock.wait();
             }
             printFirst.run();
             ++flag;
             olock.notifyAll();
         }
    }

     public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
         synchronized (olock){
             while(flag != 1){
                     olock.wait();
             }
             printSecond.run();
             ++flag;
             olock.notifyAll();
         }

    }

     public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.

         synchronized (olock){
             while(flag != 2){
                olock.wait();
             }
             printThird.run();
         }

    }

    public static void main(String[] args) {

        Foo foo = new Foo();

        Runnable ra = new Runnable() {
            @Override
            public void run() {
                System.out.println("one");

            }
        };

        Runnable rb = new Runnable() {
            @Override
            public void run() {
                System.out.println("two");

            }
        };

        Runnable rc = new Runnable() {
            @Override
            public void run() {
                System.out.println("three");

            }
        };

        try {
            foo.first(ra);
            foo.second(rb);
            foo.third(rc);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}


