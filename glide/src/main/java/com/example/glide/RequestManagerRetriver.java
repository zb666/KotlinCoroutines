package com.example.glide;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.FragmentActivity;

/**
 * @ClassName: RequestManagerRetriver
 * @Description:
 * @CreateDate: 2020/2/9
 */
public class RequestManagerRetriver {

    public RequestManager get(FragmentActivity fragmentActivity){
        return new RequestManager(fragmentActivity);
    }

    public RequestManager get(Activity activity){
        return new RequestManager(activity);
    }

    public RequestManager get(Context context){
        return new RequestManager(context);
    }
}
