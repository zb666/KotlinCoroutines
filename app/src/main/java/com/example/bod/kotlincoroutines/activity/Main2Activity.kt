package com.example.bod.kotlincoroutines.activity

import android.animation.ValueAnimator
import android.animation.ValueAnimator.RESTART
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.bod.kotlincoroutines.R
import com.example.bod.kotlincoroutines.utils.SoundHelper
import com.example.bod.kotlincoroutines.utils.SoundHelper.DING
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.view_progress.*
import kotlinx.android.synthetic.main.view_progress.view.*
import kotlinx.android.synthetic.main.view_progress.view.progressBar
import timber.log.Timber

/**
 * @ClassName: Main2Activity
 * @Description:
 * @CreateDate: 2019/9/29
 */
class Main2Activity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        ValueAnimator.ofInt(0, 17)
                .apply {
                    addUpdateListener {
                        val animatedValue = (it.animatedValue as Int) / 21
                        progressBar.progress = animatedValue.toFloat().toInt()
                    }
                    repeatMode = RESTART
                    duration = 1500
                }.start()

        bindDataAnClick(son = Base.A){

        }
    }

    val data = Base.A

    //绑定事件
    fun bindDataAnClick(son: Base, data: Data? = null, clickAction: ((Int) -> Unit)? = null) {
        //共性
        circieProgressBar.setProgressDay(data?.name?.length ?: 0)
        clickAction?.invoke(111)

        when (son) {
            is Base.A -> {

            }
            is Base.B -> {

            }
        }
    }

    //这个放在其他类中
    sealed class Base {

        abstract fun click()

        object A : Base() {
            override fun click() {
//go A
            }
        }

        object B : Base() {
            override fun click() {
//go B
            }
        }

    }

    data class Data(
            val name: String
    )

    class SingleTon private constructor(){
        companion object{
            val instance by lazy { SingleTon }
        }
    }

}
