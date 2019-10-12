package com.example.bod.kotlincoroutines.activity.neteaseui

import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.*
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bod.kotlincoroutines.MyItemDecor
import com.example.bod.kotlincoroutines.R
import com.example.bod.kotlincoroutines.adapter.ItemAdapter
import com.example.bod.kotlincoroutines.sound.TestSoundPool
import com.example.bod.kotlincoroutines.view.dp2px
import kotlinx.android.synthetic.main.activity_guide.*
import kotlinx.android.synthetic.main.layout_train_plan.*
import kotlinx.android.synthetic.main.weight_cs.*
import timber.log.Timber

class GuideActivity : BaseUiActivity(), View.OnClickListener {

    private val mPaint = Paint().apply {
        isAntiAlias = true
        color = Color.RED
        strokeWidth = 10f
    }

    private val testSoundPool by lazy { TestSoundPool() }

    private val soundList = listOf(TestSoundPool.FOCUSPLANET_NEW_APPEAR, TestSoundPool.FOCUSPLANET_COMMON_TREE)

    private fun generateCanvasBitmap(): Bitmap {
        val originBitmap = BitmapFactory.decodeResource(resources, R.drawable.asia)
                .copy(Bitmap.Config.RGB_565, true)
        //或者 val bitmap = Bitmap.createBitmap(originBitmap)
        val canvas = Canvas(originBitmap)
        canvas.drawCircle(100f, 100f, 50f, mPaint)
        return originBitmap
    }

    override fun initView() {
        ivBg.setImageBitmap(generateCanvasBitmap())

        ivBg.setOnClickListener {
            soundList.random().let {
                testSoundPool.play(it)
            }
        }
        guideList.forEach {
            it.setOnClickListener(this)
        }

        for (index in 0 until 4) {
            Timber.d("MyIndex: $index")
        }
//        start<ReStartActivity>()
        ll_jump.setOnClickListener {
            start<ReStartActivity>()
        }

        rv_divider.layoutManager = LinearLayoutManager(this)
        val itemAdapter = ItemAdapter()

        rv_divider.adapter = itemAdapter
//        rv_divider.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL).apply {
//            ContextCompat.getDrawable(this@GuideActivity,R.drawable.ic_focus_logo)?.let { setDrawable(it) }
//        })
        rv_divider.addItemDecoration(MyItemDecor(this))
        for (index in 0..30) {
            itemAdapter.addData(index.toString())
        }

        val viewGroup = window.decorView as ViewGroup
        viewGroup.addView(TextView(this).apply {
            textSize =  20f
            setTextColor(Color.RED)
            text = "Hello I'am Bob"
        }, RelativeLayout.LayoutParams(dp2px(Paint().apply {
            textSize = 20f
        }.measureText("Hello I'am Bob")),dp2px(50f)).apply {
            topMargin = dp2px(200f)
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvPaint -> {
                start<ReStartActivity>()
            }
            else -> {
            }
        }
    }

    override fun onResume() {
        super.onResume()

        ValueAnimator.ofInt(0, 18).apply {
            interpolator = LinearInterpolator()
            duration = 2000
            start()
            addUpdateListener {
                Timber.d("ProgressDay: ${it.animatedValue}")
                trainCircle.setProgress(it.animatedValue as Int)
            }
        }

        ValueAnimator.ofInt(0, 18).apply {
            interpolator = LinearInterpolator()
            duration = 2000
            start()
            addUpdateListener {
                focusCircle.progress = (it.animatedValue as Int) / 21.toFloat()
                tvFinishedDay.text = (it.animatedValue as Int).toString()
            }
        }
    }

    private val guideList by lazy {
        listOf(tvPaint)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_guide
    }

    private inline fun <reified T> start() {
        startActivity(Intent(this@GuideActivity, T::class.java))
    }

    override fun onRestart() {
        super.onRestart()
        Timber.d("OnResStart invoked")
    }

}
