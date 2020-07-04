package com.hs.factoryMode.abstractFac;

/**
 * @ClassName HuaWeiPhone
 * @Description TODO
 * @Author hsir
 * @Date 2020/6/18 下午10:09
 * @Version 1.0
 */
public class HuaWeiPhone implements IFPhone{
    @Override
    public void shutdown() {
        System.out.println("华为手机关机！");
    }

    @Override
    public void upPhone() {
        System.out.println("华为手机开机！");
    }

    @Override
    public void call() {
        System.out.println("华为手机打电话！");
    }

    @Override
    public void sendMsg() {
        System.out.println("华为手机发短信！");
    }
}
