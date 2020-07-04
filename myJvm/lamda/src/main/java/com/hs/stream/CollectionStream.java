package com.hs.stream;

import java.util.ArrayList;
import java.util.List;

public class CollectionStream {

    public static void main(String[] args) {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("a1");
        stringCollection.add("ba2");
        stringCollection.add("cc3");
        stringCollection.add("ds1");
        stringCollection.add("ea2");
        stringCollection.add("fc3");
        stringCollection.add("gd1");
        stringCollection.add("ha2");

        stringCollection
                .stream()
                .filter(s -> s.contains("a"))
                .forEach(System.out::println);

        System.out.println("--------------------");

        stringCollection
                .stream()
                .sorted((p1,p2) -> p2.compareTo(p1))
                .filter(s -> s.endsWith("2"))
                .forEach(System.out::println);

        System.out.println("--------------------");

        stringCollection
                .stream()
                .map(String::toUpperCase)
                .sorted((a,b) -> b.compareTo(a))
                .forEach(System.out::println);

        System.out.println("--------------------");

        //验证 list 中 string 是否有以 b 开头的, 匹配到第一个，即返回 true
        boolean matchStatus = stringCollection
                .stream()
                .anyMatch(s -> s.startsWith("b"));

        System.out.println(matchStatus);

        // 验证 list 中 string 是否都是以 a 开头的
        boolean allMatch = stringCollection
                .stream()
                .allMatch(s -> s.startsWith("a"));
        System.out.println(allMatch);

        // 验证 list 中 string 是否都不是以 z 开头的
        boolean noneMatch = stringCollection
                .stream()
                .noneMatch(s -> s.startsWith("z"));
        System.out.println(noneMatch);

    }
}
