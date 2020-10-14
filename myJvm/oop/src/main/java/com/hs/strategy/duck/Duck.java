package com.hs.strategy.duck;

/**
 * 不同的Duck有着不同的Quack和fly行为，封装起来
 * @Author hsir
 * @Date 2020-1-8
 * @Version 1.0
 **/
public abstract class Duck {

    public QuackBehavior quackBehavior;


    public QuackBehavior getQuackBehavior() {
        return quackBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public abstract void display();

    public void fly(){
    }

    public  void performQuack(){
        quackBehavior.quack();
    }

}
