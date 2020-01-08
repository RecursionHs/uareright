package com.hs.strategy.duck;

/**
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

    public void swim(){
    }

    public void performQuack(){
        quackBehavior.quack();
    }

}
