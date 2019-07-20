package com.example.bod.kotlincoroutines.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bod.kotlincoroutines.LogUtils
import com.example.bod.kotlincoroutines.R
import com.example.bod.kotlincoroutines.utils.printLog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        addClick()
    }

    private fun addClick() {
        tvSync.setOnClickListener {
            launch {

            }
        }
        tvAsync.setOnClickListener {
            async {

            }
        }
    }


}
