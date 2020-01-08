package com.hs.strategy.duck;

/**
 * @Author hsir
 * @Date 2020-1-8
 * @Version 1.0
 **/
public class RedDuck extends Duck {

    public RedDuck(){
        quackBehavior = new Quck();
    }

    @Override
    public void display() {
        System.out.println("i'm a red duck !");
    }

}
