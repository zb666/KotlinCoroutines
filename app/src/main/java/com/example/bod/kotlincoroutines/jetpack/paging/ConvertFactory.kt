package com.example.bod.kotlincoroutines.jetpack.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.bod.kotlincoroutines.jetpack.DateBean

/**
 *
 * @ClassName: ConvertFactory
 * @Description:
 * @CreateDate: 2019/8/1
 */
class ConvertFactory private constructor(): DataSource.Factory<Int, DateBean>() {

    companion object {
        fun newFactory() = ConvertFactory()
    }

    private val mSourceLiveDataSource by lazy {
        MutableLiveData<ConvertDataSource>()
    }

    override fun create(): DataSource<Int, DateBean> {
        val convertDataSource = ConvertDataSource()
        mSourceLiveDataSource.postValue(convertDataSource)
        return convertDataSource
    }

    fun refreshData(name:ConvertDataSource){
        mSourceLiveDataSource.postValue(name)
    }
}