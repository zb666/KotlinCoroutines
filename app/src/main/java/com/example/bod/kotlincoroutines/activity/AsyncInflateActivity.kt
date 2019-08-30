package com.example.bod.kotlincoroutines.activity

import android.graphics.BitmapFactory
import android.os.Bundle
import android.system.Os.mkdir
import android.system.Os.write
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import androidx.core.app.ActivityCompat
import com.blankj.utilcode.util.FileUtils
import com.example.bod.kotlincoroutines.LogUtils
import com.example.bod.kotlincoroutines.R
import com.example.bod.kotlincoroutines.utils.EasyUtil
import kotlinx.android.synthetic.main.activity_asyncinflate.*
import leakcanary.internal.getBytes
import java.io.*
import java.lang.Exception
import kotlin.math.min

/**
 *
 * @ClassName: AsyncInflateActivity
 * @Description:
 * @CreateDate: 2019/8/28
 */
class AsyncInflateActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asyncinflate)
        EasyUtil.checkInitPermission(this)
        startLoad()

        ActivityCompat.requestPermissions(this,
                listOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE).toTypedArray(),
                10001)

        tvMkDir.setOnClickListener {
            var file = File(cacheDir, "myFile")
            if (!file.exists()) {
                file.mkdirs()
            }
            val fileOutputStream = FileOutputStream(file)
            var bytes = "你好啊，天气很不错".getBytes()
            fileOutputStream.write(bytes)
            fileOutputStream.close()
        }

        tvMKkDirs.text = "解密"
        tvMKkDirs.setOnClickListener {
            decodeStart()
        }


    }

    fun calculateInSampleSize(options:BitmapFactory.Options,dstWidth:Int,dstHeight:Int):Int{
        val rawHeight = options.outHeight
        val rawWidth = options.outWidth
        var inSampleSize = 1
        if (rawWidth>dstWidth || rawHeight>dstHeight){
            val widthSampleSize = rawWidth/dstWidth
            val heightSampleSize = rawHeight/dstHeight
            inSampleSize = min(widthSampleSize, heightSampleSize)
        }
        return inSampleSize
    }



    private fun decodeStart() {
        val bitmapOption = BitmapFactory.Options()
        bitmapOption.inJustDecodeBounds = true
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.beach, bitmapOption)
        LogUtils.showLog("BitmapFactory", "Bitmap real " +(bitmap == null) + " w&h " + bitmapOption.outHeight + " " + bitmapOption.outWidth + " " + bitmapOption.outMimeType)

        val targetSample = calculateInSampleSize(bitmapOption, decodeIV.width, decodeIV.height)
        bitmapOption.inSampleSize = targetSample
        bitmapOption.inJustDecodeBounds = false
        val decodedBitmap = BitmapFactory.decodeResource(resources, R.drawable.beach, bitmapOption)
        //0.33MB
        LogUtils.showLog("BitmapFactory", "Bitmap real " +decodedBitmap.byteCount+(decodedBitmap == null) + decodedBitmap.width+" " + decodedBitmap.height+" w&h " + bitmapOption.outHeight + " " + bitmapOption.outWidth + " " + bitmapOption.outMimeType)

        decodeIV.setImageBitmap(decodedBitmap)

    }

    private fun mkFileDirs() {
        val cacheFile = File(externalCacheDir, "/temp/" + System.currentTimeMillis())
        if (!cacheFile.exists()) {
            val mkdirs = cacheFile.mkdirs()
            LogUtils.showLog("CacheFile", cacheFile.absolutePath + mkdirs)
        }
        writeStr(cacheFile, "aaaaaaaa")

    }

    private fun writeStr(file: File, str: String) {
        FileWriter(file).let {
            it.write(str)
            it.write("\r\n")
            it.close()
        }
    }

    private fun mkdirFile() {
        val cacheFile = File(cacheDir, "1112.txt")
        if (!cacheFile.exists()) {
            val result = cacheFile.mkdir()
            LogUtils.showLog("CacheFile", cacheFile.absolutePath + result)
        }
        cacheFile.createNewFile()
    }

    private fun startLoad() {
        var inputStream: InputStream? = null
        try {
            inputStream = assets.open("map.png")
            animation_view.progress = 0f
            animation_view.setMinAndMaxFrame(40, 63)
            animation_view.playAnimation()
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            try {
                inputStream?.close()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }
    }

}