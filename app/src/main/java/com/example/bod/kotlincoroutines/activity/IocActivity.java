package com.example.bod.kotlincoroutines.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.example.bod.kotlincoroutines.R;
import com.example.bod.kotlincoroutines.structure.iocdemo.ContentView;
import com.example.bod.kotlincoroutines.structure.iocdemo.InjectUtil;
import com.example.bod.kotlincoroutines.structure.iocdemo.OnClick;
import com.example.bod.kotlincoroutines.structure.iocdemo.OnLongClick;
import com.example.bod.kotlincoroutines.structure.iocdemo.ViewInject;
import com.example.bod.kotlincoroutines.utils.SuspensionWindowUtil;

import kotlinx.coroutines.GlobalScope;
import timber.log.Timber;

/**
 * @ClassName: IocActivity
 * @Description:
 * @CreateDate: 2020/2/21
 */
@ContentView(R.layout.activity_ioc)
public class IocActivity extends BaseActivity {

    @ViewInject(R.id.tvClick)
    private TextView mTVClick;

    @ViewInject(R.id.tvClick2)
    private TextView mTVClick2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @OnClick({R.id.tvClick,R.id.tvClick2})
    public void onBobClicked(View view){
        Timber.d("BobPerformClick: onClickInvoked()"+mTVClick.getText());
        new FlowActivity();
        new SuspensionWindowUtil(this).showSuspensionView();
    }

}
