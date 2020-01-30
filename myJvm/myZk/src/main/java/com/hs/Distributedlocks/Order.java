package com.hs.Distributedlocks;

public class Order {

    public void createOrder(){
        System.out.println(Thread.currentThread().getName() + ":创建order");
    }
}
