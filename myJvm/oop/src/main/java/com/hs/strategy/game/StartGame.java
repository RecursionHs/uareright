package com.hs.strategy.game;

/**
 * @Author hsir
 * @Date 2020-1-17
 * @Version 1.0
 **/
public class StartGame {

    public static void main(String[] args) {

        Role mechanician = new Mechanician();
        mechanician.appearance();
        mechanician.setWeapon(new AK47());
        mechanician.useWeapon();
        mechanician.jump();
    }
}
