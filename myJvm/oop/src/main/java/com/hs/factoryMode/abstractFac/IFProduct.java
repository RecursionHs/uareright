package com.hs.factoryMode.abstractFac;

/**
 * 超级抽象工厂类，这种适合不变动产品的情况
 */
public interface IFProduct {

    IFPhone ifphone();

    IFRouter ifRouter();
}
