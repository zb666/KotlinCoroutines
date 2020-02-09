package com.example.glide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.glide.resource.Value

class GlideTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide_test)

        val iFactory:IFactory = BobCallFactory().create()
        iFactory.requestResult()
    }
}
