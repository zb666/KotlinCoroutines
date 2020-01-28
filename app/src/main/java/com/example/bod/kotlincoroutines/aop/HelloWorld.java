package com.example.bod.kotlincoroutines.aop;

/**
 * @ClassName: HelloWorld
 * @Description:
 * @CreateDate: 2020/1/28
 */
public class HelloWorld {

    public void sayHello(){
        try {
            Thread.sleep(2*1000);
            System.out.println("HelloWorld: sayHello()");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
