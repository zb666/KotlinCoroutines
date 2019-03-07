package com.example.bod.kotlincoroutines

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.experimental.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val addName = LogUtils.addName()
        toast(addName)

        launch {  }

    }

    fun MainActivity.toast(logString: String) {
        Log.d("MainActivityToast", logString)
    }
}
