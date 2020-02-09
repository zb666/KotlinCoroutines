package com.example.glide;

/**
 * @ClassName: GlideBuilder
 * @Description:
 * @CreateDate: 2020/2/9
 */
public class GlideBuilder {
    public Glide build(){
        return new Glide(new RequestManagerRetriver());
    }
}
