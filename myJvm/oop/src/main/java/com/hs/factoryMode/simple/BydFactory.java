package com.hs.factoryMode.simple;

/**
 * @ClassName BydFactory
 * @Description TODO
 * @Author hsir
 * @Date 2020/6/18 下午9:38
 * @Version 1.0
 */
public class BydFactory implements Car{
    @Override
    public Car createCar() {
        return new BydCar();
    }
}
