package com.example.glide;

/**
 * @ClassName: RequestSample
 * @Description:
 * @CreateDate: 2020/2/9
 */
public class RequestSample implements IFactory {
    @Override
    public String requestResult() {
        return "Result is Bob Win";
    }
}
