package com.hs.strategy.duck;

/**
 * @Author hsir
 * @Date 2020-1-8
 * @Version 1.0
 **/
public class TestForDuck {


    public static void main(String[] args) {
        Duck redDuck = new RedDuck();
        redDuck.display();
        redDuck.performQuack();
    }
}
