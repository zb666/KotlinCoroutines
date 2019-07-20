package com.example.bod.kotlincoroutines.utils

import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

val dateFormat = SimpleDateFormat("HH:mm:ss:SSS")

val now = {
    dateFormat.format(Date(System.currentTimeMillis()))
}

fun log(msg: Any?) = println("${now()} [${Thread.currentThread().name}] $msg")


fun printLog(info:String){
    Timber.d("[%s]: $info",Thread.currentThread().name)
}