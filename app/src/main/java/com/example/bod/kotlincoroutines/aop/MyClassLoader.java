package com.example.bod.kotlincoroutines.aop;

import android.annotation.SuppressLint;
import android.os.Build;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @ClassName: MyClassLoader
 * @Description:
 * @CreateDate: 2020/1/28
 */
public class MyClassLoader extends ClassLoader {

    @SuppressLint("NewApi")
    @Override
    protected Class<?> findClass(String name) {
        String className = "D:\\Tools\\HelloWorld.class";
        byte[] classBytes = null;
        Path path = null;
        try {
                try {
                    path = Paths.get(new URI(className));
                    classBytes = Files.readAllBytes(path);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return  defineClass(className,classBytes,0,classBytes.length);
    }
}
