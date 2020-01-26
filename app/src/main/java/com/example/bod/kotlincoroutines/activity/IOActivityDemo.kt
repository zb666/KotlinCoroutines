package com.example.bod.kotlincoroutines.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.FileProvider

import com.blankj.utilcode.util.Utils
import com.example.bod.kotlincoroutines.R
import com.example.bod.kotlincoroutines.download.DownLoadManager
import kotlinx.android.synthetic.main.activity_io.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import retrofit2.http.Url

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.nio.Buffer
import java.sql.Time

import timber.log.Timber

/**
 * @ClassName: IOActivityDemo
 * @Description:
 * @CreateDate: 2020/1/13
 */
class IOActivityDemo : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_io)
        button.setOnClickListener {
            launch {
                DownLoadManager.downLoad("https://kotlinlang.org/docs/kotlin-docs.pdf", "Kotlin_doc.pdf")
                        .flowOn(Dispatchers.IO) //冷流
                        .collect {
                            when (it) {
                                is DownLoadManager.DownLoadStatus.DONE -> {
                                    Intent(Intent.ACTION_VIEW).apply {
                                        setDataAndType(
                                                FileProvider.getUriForFile(
                                                        this@IOActivityDemo, "${packageName}.provider", it.file),
                                                "application/pdf"
                                        )
                                        flags = Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_GRANT_READ_URI_PERMISSION
                                    }.also {
                                        startActivity(it)
                                    }
                                    Timber.d("FilePath:${it.file.absolutePath}")
                                }
                            }
                        }
            }
        }
    }


}
