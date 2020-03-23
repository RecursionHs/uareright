package com.hs.lamda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LamdaSort {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("linda", "jerry", "pitter");
        List<String> names2 = Arrays.asList("linda", "jerry", "pitter");
        List<String> names3 = Arrays.asList("linda", "jerry", "pitter");
        List<String> names4 = Arrays.asList("linda", "jerry", "pitter");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        char[] chars = names.get(0).toCharArray();
        System.out.println(chars[1] - chars[2]);
        System.out.println(names);
        System.out.println("---------------------");
        Collections.sort(names2,(String a,String b) -> {
            return a.compareTo(b);
        });
        System.out.println(names2);
        System.out.println("--------------------");
        Collections.sort(names3,(String a,String b) -> b.compareTo(a));
        System.out.println(names3);
        System.out.println("--------------------");
        names4.sort((a,b) -> b.compareTo(a));
        System.out.println(names4);
    }
}
