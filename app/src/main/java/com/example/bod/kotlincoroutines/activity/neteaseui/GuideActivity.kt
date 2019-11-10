package com.example.bod.kotlincoroutines.activity.neteaseui

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.SpanUtils
import com.example.bod.kotlincoroutines.MyItemDecor
import com.example.bod.kotlincoroutines.R
import com.example.bod.kotlincoroutines.adapter.ItemAdapter
import kotlinx.android.synthetic.main.activity_guide.*
import kotlinx.android.synthetic.main.layout_train_plan.*
import timber.log.Timber

class GuideActivity : BaseUiActivity(), View.OnClickListener {

    override fun onPause() {
        super.onPause()
        Timber.d("Bob: onPause")
    }

    override fun onStop() {
        super.onStop()
        Timber.d("Bob: onStop")

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Timber.d("Bob: onSaveInstanceState")

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun initView() {
        start<ReStartActivity>()
        finish()
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

        val inflateView = viewStub.inflate()

        roundIV.setImageResource(R.drawable.oceania)

        roundIV.setOnClickListener {

        }

//        iv_round.setImageResource(R.drawable.oceania)

        tv_spantest.text = SpanUtils()
                .append("专注力：").setFontSize(13, true)
                .append("73").setFontSize(30, true).setForegroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .create()
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
        Timber.d("Bob: onResume")

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
