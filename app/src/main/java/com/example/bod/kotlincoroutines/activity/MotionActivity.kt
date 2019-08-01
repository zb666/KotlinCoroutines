package com.example.bod.kotlincoroutines.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bod.kotlincoroutines.R
import com.example.bod.kotlincoroutines.jetpack.DateBean
import com.example.bod.kotlincoroutines.jetpack.MyLocationListener
import com.example.bod.kotlincoroutines.jetpack.paging.ConvertViewModel
import com.example.bod.kotlincoroutines.paging.ConvertAdapter
import com.example.bod.kotlincoroutines.viewmodel.MyViewModel
import io.reactivex.disposables.CompositeDisposable
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

    private val convertViewModel by lazy { ConvertViewModel() }

    private val disposable by lazy { CompositeDisposable() }

    private val convertAdapter = ConvertAdapter()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_scene_start)

        rv_test.layoutManager = LinearLayoutManager(this)
        rv_test.adapter = convertAdapter
        for (i in 0..10){
            convertAdapter.setTestData {
                DateBean(it.toLong(),name = "name: $it")
            }
        }

        myViewMode.getData().observe(this,Observer<DateBean> {
            tvAsync.text = it.name + it.id
        })


        tvAsync.setOnClickListener {
//            myViewMode.addData(DateBean(it.hashCode().toLong(), "随机点击"))
            val pageLiveData = convertViewModel.getPageLiveData()
            pageLiveData?.observe(this, Observer<PagedList<DateBean>> {
                it.config
            })
        }

        //这是一个函数对象
        MyLocationListener(this) {
            "$it"
        }.test()

    }

    override fun onStart() {
        super.onStart()
        disposable.add(myViewMode.getObservalData().subscribe {
//            convertAdapter.setTestData()
        })
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }


}