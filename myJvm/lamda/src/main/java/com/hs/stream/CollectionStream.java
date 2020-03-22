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
    }
}
