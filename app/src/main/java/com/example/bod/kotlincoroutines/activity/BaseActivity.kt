package com.example.bod.kotlincoroutines.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

@SuppressLint("Registered")
open class BaseActivity:AppCompatActivity(), CoroutineScope by MainScope(){
    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}

