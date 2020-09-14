package com.hs.ioc;

/**
 * @Author hsir
 * @Date 2020-9-14
 * @Version 1.0
 **/
public class SimpleIOCT {
    public static void main(String[] args) throws Exception {
        String file = SimpleIOCT.class.getClassLoader().getResource("ioc.xml").getFile();
        SimpleIOC simpleIOC = new SimpleIOC(file);
        Student stu = (Student) simpleIOC.getBean("stu");
        Teacher teacher = (Teacher) simpleIOC.getBean("teacher");
        System.out.println(stu);
        System.out.println(teacher);
    }
}
