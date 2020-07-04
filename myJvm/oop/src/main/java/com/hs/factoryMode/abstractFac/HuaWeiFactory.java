package com.hs.factoryMode.abstractFac;

/**
 * @ClassName HuaWeiFactory
 * @Description TODO
 * @Author hsir
 * @Date 2020/6/18 下午10:18
 * @Version 1.0
 */
public class HuaWeiFactory implements IFProduct{
    @Override
    public IFPhone ifphone() {
        return new HuaWeiPhone();
    }

    @Override
    public IFRouter ifRouter() {
        return new HuaWeiRouter();
    }
}
