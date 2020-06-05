package com.hs.cache;

/**
 * @ClassName User
 * @Description TODO
 * @Author hsir
 * @Date 2020/6/3 下午11:10
 * @Version 1.0
 */
public class User {
    private String userName;
    private int age;
    private String address;

    public User(String userName, int age, String address) {
        this.userName = userName;
        this.age = age;
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
