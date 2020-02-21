package com.example.bod.kotlincoroutines.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bod.kotlincoroutines.structure.iocdemo.InjectUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

@SuppressLint("Registered")
open class BaseActivity:AppCompatActivity(), CoroutineScope by MainScope(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        InjectUtil.inject(this)
    }
}

