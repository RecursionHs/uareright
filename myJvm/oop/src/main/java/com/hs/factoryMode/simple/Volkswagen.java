package com.hs.factoryMode.simple;

/**
 * @ClassName Volkswagen
 * @Description TODO
 * @Author hsir
 * @Date 2020/6/18 下午9:28
 * @Version 1.0
 */
public class Volkswagen implements Car{
    @Override
    public Car createCar() {
        return new Volkswagen();
    }
}
