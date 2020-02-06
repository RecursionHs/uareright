package com.hs.lamda.demo;

import org.junit.Test;

public class StartGame {
    public static void main(String[] args) {
        Converter<String,Integer> converter = (from) -> Integer.valueOf(from);
        Integer convert = converter.convert("123");
        System.out.println(convert);
    }

    @Test
    public void Test1(){
        Converter<String,Integer> converter = Integer::valueOf;
        Integer convert = converter.convert("123");
        System.out.println(convert);
    }

    @Test
    public void Test2(){
        Something something = new Something();
        Converter<String,String> converter = something::startWith;
        String convert = converter.convert("123");
        System.out.println(convert);
    }

    @Test
    public void Test3(){
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Hsir", "Sk");
        System.out.println(person);
    }

    @Test
    public void Test4(){
        final int num = 1;
        Converter<Integer,String> converter = (from -> String.valueOf(from + num));
        String convert = converter.convert(55);
        System.out.println(convert);
    }

    @Test
    public void Test5(){
        int num = 1;
        Converter<Integer,String> converter = (from -> String.valueOf(from + num));
        String convert = converter.convert(55);
        System.out.println(convert);
    }

    @Test
    public void Test6(){
        int num = 1;
        Converter<Integer,String> converter = (from -> String.valueOf(from + num));
        String convert = converter.convert(55);
        System.out.println(convert);
        //num = 2;
    }

    @Test
    public void Test7(){
        int num = 1;
        Converter<Integer,String> converter = (from -> {
            String d = String.valueOf(55 + num);
            //num = 3;
            return d;
        });

    }
}