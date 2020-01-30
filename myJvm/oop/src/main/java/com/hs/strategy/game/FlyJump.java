package com.hs.strategy.game;

/**
 * @Author hsir
 * @Date 2020-1-20
 * @Version 1.0
 **/
public class FlyJump implements JumpAction {

    @Override
    public void jump() {
        System.out.println("飞起来跳...");
    }
}
