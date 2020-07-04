package com.hs.factoryMode.abstractFac;

/**
 * @ClassName XiaoMiRouter
 * @Description TODO
 * @Author hsir
 * @Date 2020/6/18 下午10:14
 * @Version 1.0
 */
public class XiaoMiRouter implements IFRouter{
    @Override
    public void settingPasswd() {
        System.out.println("小米路由器设置密码！");
    }

    @Override
    public void openWifi() {
        System.out.println("小米路由器开启wifi！");
    }
}
