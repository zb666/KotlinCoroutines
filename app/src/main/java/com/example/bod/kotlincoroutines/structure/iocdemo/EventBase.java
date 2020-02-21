package com.example.bod.kotlincoroutines.structure.iocdemo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: EventBase
 * @Description: 注解的多态
 * @CreateDate: 2020/2/20
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventBase {
    String listenerSetter();
    Class<?> listenerType();
    String callbackMethod();
}
