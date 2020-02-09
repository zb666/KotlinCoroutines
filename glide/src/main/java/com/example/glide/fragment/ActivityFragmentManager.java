package com.example.glide.fragment;

import androidx.fragment.app.Fragment;

/**
 * @ClassName: ActivityFragmentManager
 * @Description:
 * @CreateDate: 2020/2/9
 */
public class ActivityFragmentManager extends Fragment {

    private LifecycleCallback lifecycleCallback;

    @SuppressWarnings("unused")
    public ActivityFragmentManager(LifecycleCallback lifecycleCallback) {
        this.lifecycleCallback = lifecycleCallback;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (lifecycleCallback!=null){
            lifecycleCallback.glideInitAction();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (lifecycleCallback!=null){
            lifecycleCallback.glideStopAction();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (lifecycleCallback!=null){
            lifecycleCallback.glideRecycleAction();
        }
    }
}
