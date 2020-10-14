package com.hs.strategy.duck;

/**
 * 策略模式：
 *  定义了算法族，分别封装起来，让它们之间可以互相替换，此模式让算法的变化独立于使用算法的客户。
 * 设计原则：
 * 1.找出应用中可能需要变化的部分，把他们独立出来，不要跟那些不需要变化的放在一起。
 * 2.针对接口编程，而不是针对于实现！
 * 3.多用组合，少用继承
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
