package com.hs;

import org.junit.Test;

/**
 * @Author hsir
 * @Date 2020-1-17
 * @Version 1.0
 **/
public class StaticDispath {

    static abstract class Human{}

    static class Man extends Human {}

    static class Woman extends Human {}


    public void sayHello(Human human){
        System.out.println("hello humen");
    }

    public void sayHello(Man man){
        System.out.println("hello man");
    }

    public void sayHello(Woman woman){
        System.out.println("hello Woman");
    }
    
    /**
     * @Author hsir
     * @Date 10:12 2020-1-17       
     * @Param [args]
     * @return void
     *         方法调用的静态分派
     *         Human woman = new Woman();
     *         左边Human为静态类型、右边Woman为实际类型
     *         重载是根据静态类型来的
     *
     **/
    public static void main(String[] args) {
        Human woman = new Woman();
        Human man = new Man();
        StaticDispath staticDispath = new StaticDispath();
        staticDispath.sayHello(woman);
        staticDispath.sayHello(man);
        staticDispath.sayHello(new Woman());
     }

    @Test
    public void tt(){
        Human woman = new Woman();
        Human man = new Man();
        StaticDispath staticDispath = new StaticDispath();
        staticDispath.sayHello(woman);
        staticDispath.sayHello(man);
    }
}
