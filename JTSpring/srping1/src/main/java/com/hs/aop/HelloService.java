package com.hs.aop;

public interface HelloService {
    void sayHelloWorld();

    public class HelloServiceImpl implements HelloService{

        @Override
        public void sayHelloWorld() {
            System.out.println("hello world");
        }
    }
}
