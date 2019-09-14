package com.example.bod.kotlincoroutines.activity

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.bod.kotlincoroutines.R
import kotlinx.android.synthetic.main.activity_glide.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import log
import timber.log.Timber

/**
 *
 * @ClassName: GlideActivity
 * @Description:
 * @CreateDate: 2019/9/5
 */

const val imageUrl = "https://focus-resource.oss-cn-beijing.aliyuncs.com/ToC/planet/entities/%E9%A3%9F%E8%9A%81%E5%85%BD.png"

class GlideActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide)
        Glide.with(this).load(imageUrl).into(imageView)

        launch {
            val result = withContext(Dispatchers.Default) {
                delay(200)
                Timber.d("BobStart")
                "with result"
            }
            Timber.d("BobFinish $result")
        }
    }

}