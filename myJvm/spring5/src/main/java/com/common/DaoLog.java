package com.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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

    /**
     * this JDK代理时，指向接口和代理类proxy，cglib代理时 指向接口和子类(不使用proxy)
     */
    @Pointcut("this(com.dao.IndexDao)")
    public void pointCutThis(){

    }

    /**
     * 此处需要注意的是，如果配置设置proxyTargetClass=false，或默认为false，则是用JDK代理，否则使用的是CGLIB代理
     * JDK代理的实现方式是基于接口实现，代理类继承Proxy，实现接口。
     * 而CGLIB继承被代理的类来实现。
     * 所以使用target会保证目标不变，关联对象不会受到这个设置的影响。
     * 但是使用this对象时，会根据该选项的设置，判断是否能找到对象。
     */
    @Pointcut("target(com.dao.IndexDaoImpl)")
    public void pointCutTarget(){

    }


    @Before("pointCutWithin()")
    public void before(JoinPoint jp){
        System.out.println("before通知。。。");
        //class com.sun.proxy.$Proxy26
        System.out.println(jp.getThis().getClass());
        //class com.dao.IndexDaoImpl
        System.out.println(jp.getTarget().getClass());

    }


    @After("pointCutWithin()")
    public void after(){
        System.out.println("after...");
    }

    @Around("pointCutWithin()")
    public void around(ProceedingJoinPoint pjp){
        System.out.println("around before");
        try {
            Object[] args = pjp.getArgs();
            for (int i = 0; i < args.length; i++) {
                args[i] += "bbbbbb";
            }
            pjp.proceed(args);//最后方法中参数变更为:hbbbbbb
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("around after");

    }
}
