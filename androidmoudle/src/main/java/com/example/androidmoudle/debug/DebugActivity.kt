package com.example.androidmoudle.debug

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidmoudle.R

/**
 *
 * @ClassName: DebugActivity
 * @Description:
 * @CreateDate: 2019/10/13
 * 切换成组件的时候将会被隔离
 */
class DebugActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debug)
    }
}