package com.hs.strategy.game;

/**
 * @Author hsir
 * @Date 2020-1-17
 * @Version 1.0
 **/
public class Mechanician extends Role {

    public Mechanician(){
        //默认武器
        setWeapon(new Gun());
        //默认跳跃方式
        setJumpAction(new ContinuousJump());

    }

    @Override
    public void appearance() {
        System.out.println("穿着皮大衣的机械师...");
    }
}
