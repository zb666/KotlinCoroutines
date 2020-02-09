package com.example.bod.kotlincoroutines.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bod.kotlincoroutines.R
import com.example.bod.kotlincoroutines.mmkvTest
import com.example.glide.GlideTestActivity
import com.tencent.mmkv.MMKV
import kotlinx.android.synthetic.main.activity_mmkv.*
import timber.log.Timber

class MMKVActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mmkv)

        val rootDir = MMKV.initialize(this)
        Timber.d("rootDir: $rootDir")
        mmkvTest()

        tv_test.setOnClickListener {
            startActivity(Intent(this,GlideTestActivity::class.java))
        }



    }
}
