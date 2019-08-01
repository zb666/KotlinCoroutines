package com.example.bod.kotlincoroutines.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

@SuppressLint("Registered")
open class BaseActivity:AppCompatActivity(), CoroutineScope by MainScope()
