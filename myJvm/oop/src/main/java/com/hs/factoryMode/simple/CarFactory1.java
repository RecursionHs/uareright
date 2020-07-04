package com.hs.factoryMode.simple;

/**
 * @ClassName CarFactory
 * @Description 不符合开闭原则
 * @Author hsir
 * @Date 2020/6/18 下午9:32
 * @Version 1.0
 */
public class CarFactory1 {
    public static Car getCar(String carName){
        if("大众".equals(carName)){
            return new Volkswagen();
        }else if("比亚迪".equals(carName)){
            return new BydCar();
        }else{
            return null;
        }
    }
}
