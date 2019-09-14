package com.example.bod.kotlincoroutines.glidefolder;

import android.content.Context;
import android.widget.ImageView;

import java.lang.ref.SoftReference;

/**
 * @ClassName: BitmapRequest
 * @Description:
 * @CreateDate: 2019/9/5
 */
public class BitmapRequest {

    private Context context;

    private String url;

    private SoftReference<ImageView> imageSoftReference;

    private IRequestListener requestListener;

    private int resId;

    private String urlMd5;//请求路径MD5加密

    public BitmapRequest with(Context context) {
        this.context = context;
        return this;
    }

    public BitmapRequest load(String url){
        this.url = url;
        this.urlMd5 = url+"md5";
        return this;
    }

    public void into(ImageView imageView){

    }



}
