package com.example.bod.kotlincoroutines.retrofit_pg;

/**
 * @ClassName: IConvert
 * @Description:
 * @CreateDate: 2020/1/31
 */
public interface IConvert {

    abstract class IFactory{
        public abstract String add();
        public abstract IConvert createConvert();
    }
}
