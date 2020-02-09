package com.example.glide;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.glide.fragment.ActivityFragmentManager;
import com.example.glide.fragment.FragmetActivityFragmentManager;

/**
 * @ClassName: RequestManager
 * @Description:
 * @CreateDate: 2020/2/9
 */
public class RequestManager {

    private final String TAG = RequestManager.class.getSimpleName();

    private final String FRAGMENT_ACTIVITY_NAME = "Fragment_Activity_NAME";
    private final String ACTIVITY_NAME = "activity_name";
    private final int NEXT_HANDLER_MSG = 995465;

    private Context reqquestManagerContext;

    private static RequestTargetEngine requestTargetEngine;

    {
        {
            if (requestTargetEngine == null) {
                requestTargetEngine = new RequestTargetEngine();
            }
        }
    }

    FragmentActivity fragmentActivity;

    public RequestManager(FragmentActivity fragmentActivity) {
        this.reqquestManagerContext = fragmentActivity;
        this.fragmentActivity = fragmentActivity;

        FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        Fragment fragment = supportFragmentManager.findFragmentByTag(FRAGMENT_ACTIVITY_NAME);
        if (fragment == null) {
            fragment = new FragmetActivityFragmentManager(requestTargetEngine);
            //添加到supportFragmentManager
            supportFragmentManager.beginTransaction().add(fragment, FRAGMENT_ACTIVITY_NAME);
        }

        Fragment fragment2 = supportFragmentManager.findFragmentByTag(ACTIVITY_NAME);
        mHandler.sendEmptyMessage(NEXT_HANDLER_MSG);
    }

    /**
     * 管理生命周期
     *
     * @param activity
     */
    public RequestManager(Activity activity) {
        this.reqquestManagerContext = activity;
//        android.app.FragmentManager supportFragmentManager = activity.getFragmentManager();
//        android.app.Fragment fragment = supportFragmentManager.findFragmentByTag(FRAGMENT_ACTIVITY_NAME);
//        if (fragment == null){
//            fragment = new FragmetActivityFragmentManager(requestTargetEngine);
//            //添加到supportFragmentManager
//            supportFragmentManager.beginTransaction().add(fragment,FRAGMENT_ACTIVITY_NAME);
//        }
//
//        Fragment fragment2 = supportFragmentManager.findFragmentByTag(ACTIVITY_NAME);
//        mHandler.sendEmptyMessage(NEXT_HANDLER_MSG);
    }

    public RequestManager(Context context) {
        this.reqquestManagerContext = context;
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Fragment fragment2 = fragmentActivity.getSupportFragmentManager().findFragmentByTag(FRAGMENT_ACTIVITY_NAME);
            return false;
        }
    });

    public RequestTargetEngine load(String path){
        mHandler.removeMessages(NEXT_HANDLER_MSG);
        requestTargetEngine.loadValueInitAction(path,reqquestManagerContext);
        return requestTargetEngine;
    }
}
