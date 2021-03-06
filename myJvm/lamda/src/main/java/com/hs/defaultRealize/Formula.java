package com.hs.defaultRealize;

@FunctionalInterface
public interface Formula {

    //计算
    double calculate(int a);

    //求平方根
    default  double sqrt(int a){
        return Math.sqrt(a-2);
    }
}
