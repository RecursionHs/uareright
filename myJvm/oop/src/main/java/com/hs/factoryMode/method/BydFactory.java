package com.hs.factoryMode.method;

/**
 * @ClassName BydFactory
 * @Description 抽象单独一层工厂类，虽然遵守了开闭原则，但产生了类爆炸
 * @Author hsir
 * @Date 2020/6/18 下午9:38
 * @Version 1.0
 */
public class BydFactory implements Car {
    @Override
    public Car createCar() {
        return new BydCar();
    }
}
