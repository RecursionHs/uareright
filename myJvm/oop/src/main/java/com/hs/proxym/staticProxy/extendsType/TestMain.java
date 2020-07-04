package com.hs.proxym.staticProxy.extendsType;

/**
 * @author : sk
 * @version V1.0
 * @Project: myJvm
 * @Package com.hs.proxym.staticProxy
 * @Description: 代理对象继承目标对象，重写需要增强的方法
 * 缺点：会代理类过多，非常复杂
 * @date Date : 2020年05月22日 下午11:11
 */
public class TestMain {

    public static void main(String[] args) {
        TargetClass targetClass = new WatchTvProxy();
        targetClass.play();
        //输出
        /**
         * i first watch tv...
         * TargetClass to play...
         * then i go to sleep...
         */
    }
}
