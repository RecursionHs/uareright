package com.hs.proxym.staticProxy.combinationType;

/**
 * @author : sk
 * @version V1.0
 * @Project: myJvm
 * @Package com.hs.proxym.staticProxy.combinationType
 * @Description: TODO
 * @date Date : 2020年05月22日 下午11:28
 */
public class CookSomeFood implements Eat {
    Eat eat;

    public CookSomeFood(Eat eat){
        this.eat = eat;
    }

    @Override
    public void eat() {
        eat.eat();
        System.out.println("烹饪食物...");
    }
}
