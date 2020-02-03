package com.example.bod.kotlincoroutines.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bod.kotlincoroutines.R;
import com.example.annotation.BindView;
import com.example.library.ButterKnife;

/**
 * @ClassName: ButterKnifeActivity
 * @Description:
 * @CreateDate: 2020/2/3
 */
public class ButterKnifeActivity extends AppCompatActivity {

    //被注解处理器扫描到 然后采集属性
    //value = activity.findViewById(R.id.tvName)
    //assignment tvName = value
    @BindView(R.id.tvName)
    TextView tvName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder);
        ButterKnife.bind(this);
        tvName.setText("BinderKnife APT注入成功");
    }
}
