package com.example.bod.kotlincoroutines.structure

import com.example.bod.kotlincoroutines.utils.log

/**
 *
 * 从细微处着手  Java的为运算
 * 位与运算
 *
 */
fun main(){
 val mMap = mapOf("1" to 123)
    mMap.get("1").run {
        log(this)
    }
}