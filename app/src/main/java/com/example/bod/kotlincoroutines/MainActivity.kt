package com.example.bod.kotlincoroutines

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val addName = LogUtils.addName()
        toast(addName)



    }



    fun MainActivity.toast(logString: String) {
        Log.d("MainActivityToast", logString)
    }
}
