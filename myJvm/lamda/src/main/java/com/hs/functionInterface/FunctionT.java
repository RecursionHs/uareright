package com.hs.functionInterface;

import java.util.function.Function;

public class FunctionT {
    public static void main(String[] args) {
        Function<Integer,Integer> A =  i -> i - 1;
        Function<Integer,Integer> B =  i -> i * 2;
        System.out.println(A.apply(5));//4
        System.out.println(B.apply(A.apply(5)));//8
        System.out.println(A.apply(B.apply(5)));//9

        System.out.println("--------------------------");
        System.out.println(A.andThen(B).apply(5));//8
        System.out.println(A.compose(B).apply(5));//9
    }
}
