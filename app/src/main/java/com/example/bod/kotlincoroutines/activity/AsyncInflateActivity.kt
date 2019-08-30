package com.example.bod.kotlincoroutines.activity

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import com.example.bod.kotlincoroutines.R
import kotlinx.android.synthetic.main.activity_asyncinflate.*
import java.io.IOException
import java.io.InputStream
import java.lang.Exception

/**
 *
 * @ClassName: AsyncInflateActivity
 * @Description:
 * @CreateDate: 2019/8/28
 */
class AsyncInflateActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asyncinflate)

        startLoad()

    }

    private fun startLoad() {
        var inputStream: InputStream? = null
        try {
            inputStream = assets.open("map.png")
            animation_view.progress = 0f
            animation_view.setMinAndMaxFrame(40,63)
            animation_view.playAnimation()
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            try {
                inputStream?.close()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }
    }

}