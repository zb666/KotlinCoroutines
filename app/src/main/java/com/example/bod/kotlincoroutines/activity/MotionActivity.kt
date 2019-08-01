package com.example.bod.kotlincoroutines.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.bod.kotlincoroutines.R
import com.example.bod.kotlincoroutines.jetpack.DateBean
import com.example.bod.kotlincoroutines.jetpack.MyLocationListener
import com.example.bod.kotlincoroutines.viewmodel.MyViewModel
import kotlinx.android.synthetic.main.activity_main_scene_start.*

/**
 *
 * @ClassName: MotionActivity
 * @Description:
 * @CreateDate: 2019/8/1
 */
class MotionActivity : AppCompatActivity() {

    private val myViewMode
        get() = ViewModelProviders.of(this).get(MyViewModel::class.java)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_scene_start)

        myViewMode.getData().observe(this, Observer<DateBean> {
            tvAsync.text = it.name + it.id
        })


        tvAsync.setOnClickListener {
            myViewMode.addData(DateBean(it.hashCode().toLong(), "随机点击"))
        }

        //这是一个函数对象
        MyLocationListener(this) {
            "$it"
        }.test()

    }


}