package com.hs.defaultRealize;

public class StartGame {

    public static void main(String[] args) {
        Calculate calculate = new Calculate();
        double sqrt = calculate.sqrt(4);
        double calculate1 = calculate.calculate(4);
        System.out.println(sqrt);
        System.out.println(calculate1);
    }
}
