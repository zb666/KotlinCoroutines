package com.example.bod.kotlincoroutines.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bod.kotlincoroutines.R
import com.example.bod.kotlincoroutines.mmkvTest
import com.tencent.mmkv.MMKV
import timber.log.Timber

class MMKVActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mmkv)

        val rootDir = MMKV.initialize(this)
        Timber.d("rootDir: $rootDir")
        mmkvTest()

    }
}
