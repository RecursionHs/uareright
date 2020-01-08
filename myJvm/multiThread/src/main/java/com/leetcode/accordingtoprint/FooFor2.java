package com.leetcode.accordingtoprint;


import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @Author hsir
 * @Date 2019-12-6
 * @Version 1.0
 **/
public class FooFor2 {

    public FooFor2(){}

    private CountDownLatch cd1 = new CountDownLatch(1);

    private CountDownLatch cd2 = new CountDownLatch(1);

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.

            printFirst.run();
            cd1.countDown();


    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
            cd1.await();
            printSecond.run();
            cd2.countDown();

    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
            cd2.await();
            printThird.run();

    }

    public static void main(String[] args) {
        FooFor2 foo = new FooFor2();

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

    @Test
    public void tt(){
        int a = 233;
        String s = String.valueOf(a);
        char[] chars = s.toCharArray();
        int result1 = 0;
        int result2 = 0;
        System.out.println(chars);
        for(int i = 0;i < chars.length;i++){
            final Integer inx = Integer.valueOf(String.valueOf(chars[i]));
            System.out.println(inx);
            if(i > 0){
                result1 = result1 * inx;
                result2 = result2 + inx;
            }else if(i == 0){
                result1 = inx;
                result2 = inx;
            }

        }
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result1  - result2);
    }

    @Test
    public void ttt(){
        int n =233;
        int sum = 0;
        int product = 1;
        while (n > 0) {
            int num = n % 10;
            sum += num;
            product *= num;
            System.out.println(n /= 10);
        }
        System.out.println(product - sum);
    }

}
