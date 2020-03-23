package com.hs.lamda.demo;


public class MemberVariable {
    //静态变量
    static int outerStaticNum;
    //成员变量
    int outerNum;

    void test1(){
        Converter<Integer,String> converter1 = (from) -> {
            //对成员变量赋值
            outerNum = 23;
            return String.valueOf(from + outerNum);
        };

        Converter<Integer,String> converter2 = (from) -> {
            //对静态变量赋值
            outerStaticNum = 23;
            return String.valueOf(from + outerStaticNum);
        };

        System.out.println(converter1.convert(55));
        System.out.println(converter2.convert(55));
    }

    public static void main(String[] args) {
        MemberVariable memberVariable = new MemberVariable();
        memberVariable.test1();
    }
}
