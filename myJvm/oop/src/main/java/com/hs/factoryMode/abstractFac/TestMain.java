package com.hs.factoryMode.abstractFac;

/**
 * @ClassName TestMain
 * @Description TODO
 * @Author hsir
 * @Date 2020/6/18 下午10:18
 * @Version 1.0
 */
public class TestMain {

    public static void main(String[] args) {
        //小米系列
        IFPhone xmPhone = new XiaoMiFactory().ifphone();
        xmPhone.upPhone();
        xmPhone.call();
        xmPhone.sendMsg();


        //华为系列
        IFRouter hwRouter = new HuaWeiFactory().ifRouter();
        hwRouter.settingPasswd();
        hwRouter.openWifi();
    }
}
