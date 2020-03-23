package com.hs.functionInterface;

import java.util.Objects;
import java.util.function.Predicate;

public class PredicateInterface {

    public static void main(String[] args) {
        Predicate<String> predicate = (s) -> s.length() > 0;
        boolean haha = predicate.test("haha");
        System.out.println(haha);//true
        System.out.println("-------------------------");
        boolean haha1 = predicate.negate().test("haha");
        System.out.println(haha1);//false
        System.out.println("-------------------------");

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        System.out.println(isEmpty.test("")); //true

        Predicate<String> isNotEmpty = String::isEmpty;
        System.out.println(isNotEmpty.negate().test("3"));//true

    }
}
