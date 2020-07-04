package com.hs.proxym.staticProxy.combinationType;

/**
 * @author : sk
 * @version V1.0
 * @Project: myJvm
 * @Package com.hs.proxym.staticProxy.combinationType
 * @Description: 目标对象和代理对象实现同一个接口，代理对象当中要包含目标对象。
 * 缺点：也会产生类爆炸，只不过比继承少一点点
 * @date Date : 2020年05月22日 下午11:29
 */
public class TestMain {

    public static void main(String[] args) {
        Eat eat = new EatImpl();
        Eat proxyCook = new CookSomeFood(eat);
        Eat eatProxy = new EatFood(proxyCook);
        eatProxy.eat();

        /**
         * 输出：
         * 先买了点菜...
         * 洗菜...
         * 烹饪食物...
         * 可以吃饭了...
         */
    }
}
