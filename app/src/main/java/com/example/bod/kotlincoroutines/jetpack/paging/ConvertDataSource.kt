package com.example.bod.kotlincoroutines.jetpack.paging

import androidx.paging.PositionalDataSource
import com.example.bod.kotlincoroutines.jetpack.DateBean

/**
 *
 * @ClassName: ConvertDataSource
 * @Description:
 * @CreateDate: 2019/8/1
 * 数据的产生
 */
class ConvertDataSource:PositionalDataSource<DateBean>() {


    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<DateBean>) {

    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<DateBean>) {

    }
}