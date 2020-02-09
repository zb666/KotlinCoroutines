package com.example.glide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.glide.resource.Value
import kotlinx.android.synthetic.main.activity_glide_test.*

class GlideTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide_test)

        com.bumptech.glide.Glide.with(this).load("https://cn.bing.com/sa/simg/hpb/LaDigue_EN-CA1115245085_1920x1080.jpg").into(iv_load1)

        iv_load1.setOnClickListener {
            Glide.with(this).load("https://cn.bing.com/sa/simg/hpb/LaDigue_EN-CA1115245085_1920x1080.jpg")
                    .into(iv_load1)
            Glide.with(this).load("https://cn.bing.com/sa/simg/hpb/LaDigue_EN-CA1115245085_1920x1080.jpg").into(iv_load2)
            Glide.with(this).load("https://cn.bing.com/sa/simg/hpb/LaDigue_EN-CA1115245085_1920x1080.jpg").into(iv_load3)

        }


        val iFactory: IFactory = BobCallFactory().create()
        iFactory.requestResult()

        Runtime.getRuntime().gc()
    }
}
