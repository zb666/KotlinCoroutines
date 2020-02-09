package com.example.glide;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.FragmentActivity;

/**
 * @ClassName: Glide
 * @Description:
 * @CreateDate: 2020/2/9
 */
public class Glide {

    RequestManagerRetriver retriver;

    public Glide(RequestManagerRetriver retriver) {
        this.retriver = retriver;
    }

    public static RequestManager with(FragmentActivity fragmentActivity){
        return getRetriever(fragmentActivity).get(fragmentActivity);
    }

    public static RequestManager with(Context context){
        return getRetriever(context).get(context);
    }

    public static RequestManager with(Activity activity){
        return getRetriever(activity).get(activity);
    }

    //RequestManager让RequestManagerRetriver去创建
    public static RequestManagerRetriver getRetriever(Context context){
        return  Glide.get(context).getRetriver();
    }

    public static Glide get(Context context){
        return new GlideBuilder().build();
    }
    public RequestManagerRetriver getRetriver(){
        return retriver;
    }
}
