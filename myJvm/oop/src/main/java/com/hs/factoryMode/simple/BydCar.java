package com.hs.factoryMode.simple;

/**
 * @ClassName BydCar
 * @Description TODO
 * @Author hsir
 * @Date 2020/6/18 下午9:32
 * @Version 1.0
 */
public class BydCar implements Car{
    @Override
    public Car createCar() {
        return new BydCar();
    }
}
