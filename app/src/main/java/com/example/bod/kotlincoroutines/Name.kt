package com.example.bod.kotlincoroutines

import kotlinx.coroutines.delay

/**
 *
 * @ClassName: Name
 * @Description:
 * @CreateDate: 2019/7/31
 */
class Name  {

    private var gender:Boolean = true

    private val count = 1000

    constructor(){
        LogUtils.showLog("Init","主构造函数 $gender")
    }

    constructor(name:String):this(){
        LogUtils.showLog("Init","从构造函数$gender")
    }

    init {
        gender = false
        LogUtils.showLog("Init","Init 代码块 $count")
    }


    //这里就是类似函数的回调
    suspend fun getToken(name:String,onSuccess:(String)->Unit){
        delay(30_000)
        val s = name + name + name
        onSuccess(s)
    }

}