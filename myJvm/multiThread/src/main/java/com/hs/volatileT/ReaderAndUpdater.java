package com.hs.volatileT;


public class ReaderAndUpdater {
    final static int MAX_VALUE = 50;
    static volatile int init_value;

    public static void main(String[] args) {
        new Thread(()->{
            int localValue = init_value;
            while (localValue < MAX_VALUE){
                if(localValue < MAX_VALUE){
                    System.out.println("Reader : " + init_value);
                    localValue = init_value;
                }
            }
        },"Reader").start();

        new Thread(()->{
            int localValue = init_value;
            while (localValue < MAX_VALUE){
                    System.out.println("!!!!!!!!!!!!Write : " + (++localValue));
                    init_value = localValue ;
                   /* try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
            }
        },"Write").start();

    }

}
