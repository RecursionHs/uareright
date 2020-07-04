package com.hs.thrift.impl;

import com.hs.thrift.HelloWorldService;
import org.apache.thrift.TException;

public class HelloWorldImpl implements HelloWorldService.Iface {
    public String sayHello(String username) throws TException {
        return "hi my friend : " + username;
    }
}
