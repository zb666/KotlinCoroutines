package com.example.library;

import java.lang.annotation.Target;

/**
 * @ClassName: ViewBinder
 * @Description:
 * @CreateDate: 2020/2/3
 */
public interface ViewBinder<T> {
    void bind(T target);
}
