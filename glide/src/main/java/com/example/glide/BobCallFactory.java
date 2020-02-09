package com.example.glide;

/**
 * @ClassName: BobCallFactory
 * @Description:
 * @CreateDate: 2020/2/9
 */
public class BobCallFactory extends IFactory.CallAdapter {
    @Override
    IFactory create() {
        return new RequestSample();
    }
}
