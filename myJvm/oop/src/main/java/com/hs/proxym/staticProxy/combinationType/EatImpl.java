package com.hs.proxym.staticProxy.combinationType;

/**
 * @author : sk
 * @version V1.0
 * @Project: myJvm
 * @Package com.hs.proxym.staticProxy.combinationType
 * @Description: TODO
 * @date Date : 2020年05月22日 下午11:26
 */
public class EatImpl implements  Eat{

    @Override
    public void eat() {
        System.out.println("洗菜...");
    }
}
