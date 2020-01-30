package com.hs.lamda.demo;

public class StartGame {
    public static void main(String[] args) {
        Converter<String,Integer> converter = (from) -> Integer.valueOf(from);
        Integer convert = converter.convert("123");
        System.out.println(convert);
    }
}
