package com.hs.aop;

import org.junit.Test;

public class SimpleAopTest {

    @Test
    public void getProxy(){
        MethodInvocation logTask = () -> System.out.println("log task start");
        HelloService.HelloServiceImpl helloServiceImpl = new HelloService.HelloServiceImpl();

        BeforeAdvice beforeAdvice = new BeforeAdvice(helloServiceImpl, logTask);

        HelloService helloServiceImplProxy = (HelloService)SimpleAop.getProxy(helloServiceImpl,beforeAdvice);

        helloServiceImplProxy.sayHelloWorld();
    }
}
