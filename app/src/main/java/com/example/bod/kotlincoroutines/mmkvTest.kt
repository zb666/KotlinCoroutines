package com.example.bod.kotlincoroutines

import com.tencent.mmkv.MMKV
import timber.log.Timber

/**
 *
 * @ClassName: mmkvTest
 * @Description:
 * @CreateDate: 2020/2/8
 */
fun mmkvTest(){
    val mmkv = MMKV.defaultMMKV()
    mmkv.encode("bool",true)
    val boolResult = mmkv.decodeBool("bool")

    mmkv.encode("Bob","ZB666")
    val decodeResult = mmkv.decodeString("Bob")
    Timber.d("Result: $decodeResult $boolResult")
}