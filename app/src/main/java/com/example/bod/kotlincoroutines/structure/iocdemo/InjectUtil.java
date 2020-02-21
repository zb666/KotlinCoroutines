package com.example.bod.kotlincoroutines.structure.iocdemo;

import android.content.Context;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName: InjectUtil
 * @Description:
 * @CreateDate: 2020/2/21
 */
public class InjectUtil {


    public static void inject(Object context) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        injectLayout(context);
        injectClick(context);
        injectId(context);
    }

    //布局的注入
    public static void injectLayout(Object context) throws NoSuchMethodException {
        Class<?> tClass = context.getClass();
        ContentView typeContentView = tClass.getAnnotation(ContentView.class);
        if (typeContentView == null)
            return;
        int value = typeContentView.value();
        Method method = tClass.getMethod("setContentView", int.class);
        try {
            method.invoke(context, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public static void injectId(Object context) throws NoSuchMethodException {
        Class<?> tClass = context.getClass();
        Field[] fields = tClass.getDeclaredFields();
        for (Field field : fields) {
            ViewInject annotation = field.getAnnotation(ViewInject.class);
            if (annotation != null) {
                //方法
                Method fidMethod = tClass.getMethod("findViewById", int.class);
                fidMethod.setAccessible(true);
                try {
                    //decorView.findViewById(R.id.tv_context)
                    View targetView = (View) fidMethod.invoke(context, annotation.value());
                    //开通权限进行赋值
                    field.setAccessible(true);
                    //赋值 手工
                    field.set(context, targetView);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void injectClick(Object context) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //onClick()->代理类中执行
        Class<?> tClass = context.getClass();
        Method[] methods = tClass.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationClass = annotation.annotationType();
                EventBase eventBaseAnnotation = annotationClass.getAnnotation(EventBase.class);
                if (eventBaseAnnotation == null) return;

                String callbackMethod = eventBaseAnnotation.callbackMethod();
                String listenerSetter = eventBaseAnnotation.listenerSetter();
                Class<?> listenerType = eventBaseAnnotation.listenerType();

                Method valueMethod = annotationClass.getDeclaredMethod("value");
                int[] valueIds = (int[]) valueMethod.invoke(annotation);
                for (int valueId : valueIds) {
                    Method findViewById = tClass.getMethod("findViewById", int.class);
                    View view = (View) findViewById.invoke(context, valueId);
                    if (view == null) {
                        continue;
                    }
                    ListenerInvocationHandler invocationHandler = new ListenerInvocationHandler(context, method);
                    Object onClickListenerProxy = Proxy.newProxyInstance(
                            listenerType.getClassLoader(),
                            new Class[]{listenerType},
                            invocationHandler);
                    //tvClick.setOnClickListener->clickProxy代理类->onBobClicked(View view)参数view是onClick(View v)中的v
                    Method clickMethodOnView = view.getClass().getMethod(listenerSetter, listenerType);
                    clickMethodOnView.invoke(view, onClickListenerProxy);
                }
            }
        }

    }


}
