package com.example.bod.kotlincoroutines.activity.neteaseui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 *
 * @ClassName: BaseUiActivity
 * @Description:
 * @CreateDate: 2019/10/1
 */
abstract class BaseUiActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
    }

    abstract fun initView()

    protected abstract fun getLayoutId(): Int


}