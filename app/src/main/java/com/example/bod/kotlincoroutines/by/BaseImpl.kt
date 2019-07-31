package com.example.bod.kotlincoroutines.by

import android.util.Log

/**
 *
 * @ClassName: BaseImpl
 * @Description:
 * @CreateDate: 2019/7/31
 */
class BaseImpl(var name: String) : IBase {
    override fun print() {
//bug fix
        Log.d("Bob", "$name")
    }
}