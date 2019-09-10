package com.hs.volatileT;

public class AddNumber {
    private static volatile int initNum = 0;

    public static void addNum(){
        ++initNum;
    }

    public synchronized static void main(String[] args) {
        for(int i = 0;i < 10;i++){
            for(int j = 0;j < 5;j++){
                new Thread(()->{
                    addNum();
                    System.out.println(initNum);
                }).start();
            }
        }
    }
}
