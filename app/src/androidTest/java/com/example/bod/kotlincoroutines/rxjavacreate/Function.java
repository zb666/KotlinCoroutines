package com.example.bod.kotlincoroutines.rxjavacreate;

/**
 * @ClassName: Function
 * @Description:
 * @CreateDate: 2019/10/23
 */
public interface Function<T,R> {
     R apply(T t);//类型变化
}
