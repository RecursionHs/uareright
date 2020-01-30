package com.hs;

/**
 * @Author hsir
 * @Date 2020-1-17
 * @Version 1.0
 **/
public class DynamicDispath {
    /**
     * @Author hsir
     * @Date 11:18 2020-1-17
     * @Param [args]
     * @return void
     * 方法重写后，方法的执行为动态分派
     **/
    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
        man = new Woman();
        man.sayHello();
    }

}
abstract class Human{
    abstract void sayHello();
}

class Man extends Human{
    @Override
    void sayHello() {
        System.out.println("hello Man");
    }
}

class Woman extends Human{
    @Override
    void sayHello() {
        System.out.println("hello woman");
    }
}
