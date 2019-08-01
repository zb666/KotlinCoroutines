package com.example.bod.kotlincoroutines.jetpack

/**
 *
 * @ClassName: DataRepository
 * @Description:
 * @CreateDate: 2019/8/1
 */
class DataRepository {

    private val dataList = ArrayList<DateBean>()

    init {
        for (i in 0..100){
            dataList.add(DateBean(id = i.toLong(),name = "name：$i"))
        }
    }



}