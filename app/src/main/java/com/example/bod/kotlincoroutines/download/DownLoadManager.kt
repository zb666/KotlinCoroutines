package com.example.bod.kotlincoroutines.download

import com.blankj.utilcode.util.Utils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream

/**
 *
 * @ClassName: DownLoadManager
 * @Description:
 * @CreateDate: 2020/1/13
 */
object DownLoadManager {

    private val downLoadDirectory by lazy {
        File(Utils.getApp().filesDir,"download").apply {
            mkdirs()
        }
    }


    sealed class DownLoadStatus{
        class DONE(val file:File):DownLoadStatus()
    }

    fun downLoad(url:String,fileName:String):Flow<DownLoadStatus>{
        val downFile = File(downLoadDirectory,fileName)
        return flow {
            val request = Request.Builder().url(url).get().build()
            val response = okHttpClient.newCall(request).execute()
            if (response.isSuccessful){
                response.body()!!.let {body->
                    val fileOutputStream = FileOutputStream(downFile)
                    fileOutputStream.use {output->
                        body.byteStream().use {input->
                            input.copyTo(output)//copy value to Target Value
                        }
                    }
                    emit(DownLoadStatus.DONE(downFile))
                }
            }
        }
    }
}