package com.hs.lamda.demo;

/**
 * functionalInterface是一种约束
 * lamda简写是在一个接口只有一个抽象方法的情况，所以要使用lamda简写，接口中只能有一个抽象方法
 * @param <F>
 * @param <T>
 */
@FunctionalInterface
public interface Converter<F,T> {
    T convert(F from);
}
