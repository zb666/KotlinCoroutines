package com.example.bod.kotlincoroutines

import com.blankj.utilcode.util.CacheDiskUtils

/**
 *
 * @ClassName: Worker
 * @Description:
 * @CreateDate: 2019/9/10
 */
class Worker(val func: (String) -> Unit) {

    fun statyPrint(str: String) {
        func.invoke(str)
    }

}