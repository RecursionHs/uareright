package com.hs.defaultRealize;

import org.junit.Test;

import static java.lang.Math.sqrt;

public class StartGame {

    public static void main(String[] args) {
        Calculate calculate = new Calculate();
        double sqrt = calculate.sqrt(4);
        double calculate1 = calculate.calculate(4);
        System.out.println(sqrt);
        System.out.println(calculate1);
    }

    @Test
    public void Test1(){
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a);
            }
        };
        System.out.println(formula.calculate(6));
    }

    @Test
    public void Test2(){
        Formula formula = (a)-> sqrt(a);
        System.out.println(formula.calculate(6));
    }
}
