package com.hs.factoryMode.abstractFac;

/**
 * @ClassName XiaoMiPhone
 * @Description TODO
 * @Author hsir
 * @Date 2020/6/18 下午10:08
 * @Version 1.0
 */
public class XiaoMiPhone implements IFPhone{
    @Override
    public void shutdown() {
        System.out.println("小米手机关机！");
    }

    @Override
    public void upPhone() {
        System.out.println("小米手机开机！");
    }

    @Override
    public void call() {
        System.out.println("小米手机打电话！");
    }

    @Override
    public void sendMsg() {
        System.out.println("小米手机发短信！");
    }
}
