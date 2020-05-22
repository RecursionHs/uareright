package com.hs.proxym.staticProxy.extendsType;

/**
 * @author : sk
 * @version V1.0
 * @Project: myJvm
 * @Package com.hs.proxym.staticProxy
 * @Description: TODO
 * @date Date : 2020年05月22日 下午11:18
 */
public class WatchTvProxy extends SleepProxy {
    @Override
    public void play() {
        System.out.println("i first watch tv...");
        super.play();
    }
}
