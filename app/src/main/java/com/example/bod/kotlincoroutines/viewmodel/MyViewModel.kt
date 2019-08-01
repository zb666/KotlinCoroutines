package com.example.bod.kotlincoroutines.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bod.kotlincoroutines.jetpack.DateBean

/**
 *
 * @ClassName: MyViewModel
 * @Description:
 * @CreateDate: 2019/8/1
 */
class MyViewModel:ViewModel() {

    private val liveData by lazy { MutableLiveData<DateBean>() }

    fun addData(dataBean: DateBean){
        liveData.postValue(dataBean)
    }

    fun getData():MutableLiveData<DateBean> = liveData

}