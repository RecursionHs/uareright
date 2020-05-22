package com.hs.proxym.staticProxy.extendsType;

/**
 * @author : sk
 * @version V1.0
 * @Project: myJvm
 * @Package com.hs.proxym.staticProxy
 * @Description: TODO
 * @date Date : 2020年05月22日 下午11:13
 */
public class SleepProxy extends TargetClass {
    @Override
    public void play() {
        super.play();
        System.out.println("then i go to sleep...");
    }
}
