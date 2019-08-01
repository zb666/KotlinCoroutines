package com.example.bod.kotlincoroutines.jetpack.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.bod.kotlincoroutines.jetpack.DateBean

/**
 *
 * @ClassName: ConvertViewModel
 * @Description:
 * @CreateDate: 2019/8/1
 */
class ConvertViewModel:ViewModel() {

    private val convertList
    get() = ConvertFactory.newFactory()

    private val pagedlist: LiveData<PagedList<DateBean>>? = LivePagedListBuilder(convertList, 20).build()


    init {
        //initdate
    }

    fun getPageLiveData() = pagedlist

    fun refreshData(name:ConvertDataSource){
       convertList.refreshData(name)
    }

}