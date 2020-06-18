package com.hs.factoryMode.method;

import com.hs.factoryMode.simple.BydFactory;
import com.hs.factoryMode.simple.Car;

/**
 * @ClassName TestMain
 * @Description TODO
 * @Author hsir
 * @Date 2020/6/18 下午9:35
 * @Version 1.0
 */
public class TestMain {
    public static void main(String[] args) {

        Car car = new BydFactory().createCar();
        System.out.println(car);

    }
}
