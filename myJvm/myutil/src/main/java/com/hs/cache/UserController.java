package com.hs.cache;
/**
 *@ClassName UserController
 *@Author hsir
 *@Date 2020/6/3 下午11:09
 *@Version 1.0
 */
public class UserController {

    private User[] users = {
       new User("张三",12,"北京"),
       new User("赵四",12,"北京"),
       new User("王五",11,"北京"),
       new User("钱六",14,"北京"),
       new User("田七",125,"北京"),
       new User("老八",16,"北京")
    };

    /**
     * 这里缓存了方法返回打对象，而key为tom::userid的形式
     * @param userId
     * @return
     */
    @MyCache(key = "tom")
    public User getUser(Integer userId){
        System.out.println("进入方法...");
        User user = users[userId];
        System.out.println(user);
        return user;
    }

    @MyCache(key = "tom")
    public User getUser(){
        System.out.println("进入方法...");
        User user = users[0];
        return user;
    }

}
