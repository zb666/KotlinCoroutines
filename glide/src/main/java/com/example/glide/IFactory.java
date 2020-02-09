package com.example.glide;

/**
 * @ClassName: IFactory
 * @Description:
 * @CreateDate: 2020/2/9
 */
public interface IFactory {

    String requestResult();

    abstract class CallAdapter{
       abstract IFactory create();
    }

}
