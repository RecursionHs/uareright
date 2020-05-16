package com.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Pointcut各类join point连接点控制的大小粒度不一样。
 */
@Component
@Aspect
public class DaoLog {
    /**
     * execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern) throws-pattern?)
     * 这里问号表示当前项可以有也可以没有，其中各项的语义如下
     * modifiers-pattern：方法的可见性，如public，protected；
     * ret-type-pattern：方法的返回值类型，如int，void等；
     * declaring-type-pattern：方法所在类的全路径名，如com.spring.Aspect；
     * name-pattern：方法名类型，如buisinessService()；
     * param-pattern：方法的参数类型，如java.lang.String；
     * throws-pattern：方法抛出的异常类型，如java.lang.Exception；
     */
    @Pointcut("execution(* com.dao.*.*(..))")
    public void pointCutExecution(){

    }

    /**
     * 表达式的最小粒度为类
     * within与execution相比，粒度更大，仅能实现到包和接口、类级别。而execution可以精确到方法的返回值，参数个数、修饰符、参数类型等
     * @Pointcut("within(com.chenss.dao.*)")//匹配com.chenss.dao包中的任意方法
     * @Pointcut("within(com.chenss.dao..*)")//匹配com.chenss.dao包及其子包中的任意方法
     */
    @Pointcut("within(com.dao..*)")
    public void pointCutWithin(){

    }

    /**
     * args表达式的作用是匹配指定参数类型和指定参数数量的方法,与包名和类名无关
     */
    @Pointcut("args(java.lang.String)")
    public void pointCutArgs(){

    }




    @Before("pointCutArgs()")
    public void before(){
        System.out.println("before通知。。。");
    }
}
