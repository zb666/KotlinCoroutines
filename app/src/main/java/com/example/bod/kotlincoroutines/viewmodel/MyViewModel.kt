package com.example.bod.kotlincoroutines.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bod.kotlincoroutines.jetpack.DateBean
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

/**
 *
 * @ClassName: MyViewModel
 * @Description:
 * @CreateDate: 2019/8/1
 */
class MyViewModel:ViewModel() {

    private val liveData by lazy { MutableLiveData<DateBean>() }
    //这里可以配合Room

    fun addData(dataBean: DateBean){
        liveData.postValue(dataBean)
    }

    fun getData():MutableLiveData<DateBean> = liveData

    fun getObservalData() = Observable.interval(0,3,TimeUnit.SECONDS)


}