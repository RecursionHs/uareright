package com.hs.strategy.game;

/**
 * @Author hsir
 * @Date 2020-1-17
 * @Version 1.0
 **/
public abstract class Role {

    //武器
    public Weapon weapon;

    //跳跃
    public JumpAction jumpAction;

    //设置装备
    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }

    //设置跳跃方式
    public void setJumpAction(JumpAction jumpAction){
        this.jumpAction = jumpAction;
    }

    public void useWeapon(){
        weapon.killTheMonster();
    }

    public void jump(){
        jumpAction.jump();
    }
    public abstract void appearance();

}
