package com.hs.strategy.duck;

/**
 * @Author hsir
 * @Date 2020-1-8
 * @Version 1.0
 **/
public class Quck implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("i can quack");
    }

}
