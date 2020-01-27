package com.example.bod.kotlincoroutines.activity

import android.os.Bundle
import com.example.bod.kotlincoroutines.R
import java.net.Socket

class OkHttpMainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_okhttp_main)
    }

    private val mSocket = Socket("127.0.0.1",1080)
    private val runnable = Runnable {

    }
}
