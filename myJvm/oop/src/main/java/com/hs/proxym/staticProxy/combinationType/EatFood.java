package com.hs.proxym.staticProxy.combinationType;

import com.hs.proxym.staticProxy.combinationType.Eat;

/**
 * @author : sk
 * @version V1.0
 * @Project: myJvm
 * @Package com.hs.proxym.staticProxy.extendsType
 * @Description: TODO
 * @date Date : 2020年05月22日 下午11:34
 */
public class EatFood implements Eat {
    Eat eat;

    public EatFood(Eat eat){
        this.eat = eat;
    }

    @Override
    public void eat() {
        System.out.println("先买了点菜...");
        eat.eat();
        System.out.println("可以吃饭了...");
    }
}
