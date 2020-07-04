package com.hs.factoryMode.abstractFac;

/**
 * @ClassName XiaoMiFactory
 * @Description TODO
 * @Author hsir
 * @Date 2020/6/18 下午10:17
 * @Version 1.0
 */
public class XiaoMiFactory implements IFProduct{
    @Override
    public IFPhone ifphone() {
        return new XiaoMiPhone();
    }

    @Override
    public IFRouter ifRouter() {
        return new XiaoMiRouter();
    }
}
