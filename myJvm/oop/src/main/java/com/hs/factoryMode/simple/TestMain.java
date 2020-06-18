package com.hs.factoryMode.simple;

/**
 * @ClassName TestMain
 * @Description TODO
 * @Author hsir
 * @Date 2020/6/18 下午9:35
 * @Version 1.0
 */
public class TestMain {
    public static void main(String[] args) {
/*        Car car = CarFactory1.getCar("比亚迪");
        System.out.println(car);*/
        Car car = new BydFactory().createCar();
        System.out.println(car);

    }
}
