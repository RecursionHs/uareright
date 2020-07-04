package com.hs.factoryMode.abstractFac;

/**
 * @ClassName HuaWeiRouter
 * @Description TODO
 * @Author hsir
 * @Date 2020/6/18 下午10:15
 * @Version 1.0
 */
public class HuaWeiRouter implements IFRouter{
    @Override
    public void settingPasswd() {
        System.out.println("华为路由器设置密码！");
    }

    @Override
    public void openWifi() {
        System.out.println("华为路由器开启wifi！");
    }
}
