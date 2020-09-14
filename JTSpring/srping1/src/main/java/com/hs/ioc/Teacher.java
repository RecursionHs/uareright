package com.hs.ioc;


public class Teacher {
    private String name;
    private String classStu;
    private Student stu;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassStu() {
        return classStu;
    }

    public void setClassStu(String classStu) {
        this.classStu = classStu;
    }

    public Student getStu() {
        return stu;
    }

    public void setStu(Student stu) {
        this.stu = stu;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", classStu='" + classStu + '\'' +
                ", stu=" + stu +
                '}';
    }
}
