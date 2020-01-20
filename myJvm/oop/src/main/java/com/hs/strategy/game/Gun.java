package com.hs.strategy.game;

/**
 * @Author hsir
 * @Date 2020-1-20
 * @Version 1.0
 **/
public class Gun implements Weapon{
    @Override
    public void killTheMonster() {
        System.out.println("拿着手枪打小怪");
    }
}
