package com.example.bod.kotlincoroutines.activity.neteaseui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bod.kotlincoroutines.MyItemDecor
import com.example.bod.kotlincoroutines.R
import com.example.bod.kotlincoroutines.adapter.ItemAdapter
import kotlinx.android.synthetic.main.activity_guide.*
import timber.log.Timber

class GuideActivity : BaseUiActivity(), View.OnClickListener {

    override fun initView() {
        guideList.forEach {
            it.setOnClickListener(this)
        }

        for (index in 0 until 4){
            Timber.d("MyIndex: $index")
        }
        start<ReStartActivity>()
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
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvPaint-> {
                start<ReStartActivity>()
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

    override fun onRestart() {
        super.onRestart()
        Timber.d("OnResStart invoked")
    }

}
