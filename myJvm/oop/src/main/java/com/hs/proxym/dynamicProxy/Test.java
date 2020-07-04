package com.hs.proxym.dynamicProxy;

import com.hs.proxym.staticProxy.combinationType.Eat;

import java.util.Random;
import java.util.UUID;

/**
 * @author : sk
 * @version V1.0
 * @Project: myJvm
 * @Package com.hs.proxym.dynamicProxy
 * @Description: TODO
 * @date Date : 2020年05月25日 下午10:58
 */
public class Test implements Eat {
    public static void main(String[] args) {
        Test test = new Test();
        String s = test.getClass().getPackage().toString();
        String substring = s.substring(0, s.lastIndexOf(".")-1);
        System.out.println(substring);
        System.out.println(s);
        System.out.println(test.getClass().getInterfaces()[0].getName());
        System.out.println(UUID.randomUUID().toString().substring(0,6));
        System.out.println(test.getClass().getResource("/").getPath());
        System.out.println((char)47);
    }

    @Override
    public void eat() {

    }
}
