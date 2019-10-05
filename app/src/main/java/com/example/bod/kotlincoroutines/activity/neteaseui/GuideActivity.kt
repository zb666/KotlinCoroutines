package com.example.bod.kotlincoroutines.activity.neteaseui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.bod.kotlincoroutines.R
import kotlinx.android.synthetic.main.activity_guide.*

class GuideActivity : BaseUiActivity(), View.OnClickListener {

    override fun initView() {
        guideList.forEach {
            it.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvPaint-> {
                start<PaintActivity>()
            }
            else -> {
            }
        }
    }

    private val guideList by lazy {
        listOf(tvPaint)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_guide
    }


    private inline fun <reified T> start(){
        startActivity(Intent(this@GuideActivity,T::class.java))
    }

}
