package com.hs.strategy.game;

/**
 * @Author hsir
 * @Date 2020-1-20
 * @Version 1.0
 **/
public class ContinuousJump implements JumpAction {
    @Override
    public void jump() {
        System.out.println("可以连跳两下...");
    }
}
