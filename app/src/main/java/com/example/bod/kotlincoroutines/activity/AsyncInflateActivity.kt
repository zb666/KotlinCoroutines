package com.example.bod.kotlincoroutines.activity

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.example.bod.kotlincoroutines.LogUtils
import com.example.bod.kotlincoroutines.R
import com.example.bod.kotlincoroutines.UPush
import com.example.bod.kotlincoroutines.utils.EasyUtil
import com.example.bod.kotlincoroutines.zip
import kotlinx.android.synthetic.main.activity_asyncinflate.*
import kotlinx.coroutines.*
import leakcanary.internal.getBytes
import timber.log.Timber
import java.io.*
import kotlin.math.min

/**
 *
 * @ClassName: AsyncInflateActivity
 * @Description:
 * @CreateDate: 2019/8/28
 */
class AsyncInflateActivity : BaseActivity() {

    //out -> extends 生产和io流一样
    //in ->super 消费，写入


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asyncinflate)
        EasyUtil.checkInitPermission(this)
        startLoad()

        val intent = Intent().apply {
            action = "com.example.bod.kotlincoroutines.activity.GlideActivity"
            this.addCategory("android.intent.category.DEFAULT")
        }
        startActivity(intent)

        tvGlide.setOnClickListener {
            startActivity(Intent(it.context, GlideActivity::class.java))
        }
        ActivityCompat.requestPermissions(this,
                listOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE).toTypedArray(),
                10001)

        tvMkDir.setOnClickListener {
            var file = File(cacheDir, "myFile")
            if (!file.exists()) {
                file.mkdir()
            }
            val fileOutputStream = FileOutputStream(file)
            var bytes = "你好啊，天气很不错".getBytes()
            fileOutputStream.write(bytes)
            fileOutputStream.close()
        }

        tvMKkDirs.text = packageName
        tvMKkDirs.setOnClickListener {
            doSave()
        }

        com.blankj.utilcode.util.LogUtils.dTag("BobTest", UPush.A.string + " " + UPush.B.string)

        launch(Dispatchers.Default) {
            Timber.d("aaaaa start")

            val asyncOne = async {
                Timber.d("aaaaa 111挂起")
                delay(5000) // 挂起协程 但是不会造成当前线程的阻塞 //sleep会造成当前现成的阻塞
                Timber.d("aaaaa ${Thread.currentThread().name} aaaa挂起结束")
                "111"
            }

            //收到外层作用域的影响
            val asyncTwo = async {
                Timber.d("aaaaa 222挂起")
                delay(3000)
                Timber.d("aaaaa ${Thread.currentThread().name} bbbb挂起结束")
                "222"
            }

            listOf(asyncOne, asyncTwo).forEach {
                Timber.d("aaaaa end ${it.await()}")
            }
            coroutineScope { }

            //这里只是等待值 但是协程已经启动了，这里只是等回调而已，并不会造成async中获取结果过程的阻塞
            //但是不能在async之后立马使用await，这样会造成阻塞

        }


        launch {
            zip({ aaa() }, { bbb() }, { ccc() })
            Timber.d("hhhhhhh inited()")
        }


    }


    suspend fun aaa() {
        delay(2000)
        Timber.d("hhhhhhh aaastartlaod ${Thread.currentThread().name}")
    }

    suspend fun bbb() {
        //这里是一个作用域  所以是累加的
        //前一个值等待后一个数据的值 所以是累加的
        delay(1_000)
        coroutineScope {
            Timber.d("hhhhhhh bbbstartlaod")

            val aaa = withContext(Dispatchers.IO) {
                delay(2_000)
                "111"
            }
            val bbb = withContext(Dispatchers.IO) {
                delay(3000)
                "222"
            }
            Timber.d("hhhhhhh bbbendload ${Thread.currentThread().name} $aaa $bbb")
        }

    }

    suspend fun ccc() {
        delay(4_000)
        Timber.d("hhhhhhh cccstartlaod ${Thread.currentThread().name}")
    }


    private fun doSave() {

        val fileName = "myfile"

        val str = "Hello World"

        val fileOutPutStream = openFileOutput("brainco.txt", Context.MODE_PRIVATE)
        fileOutPutStream.write("数据写入".getBytes())
        fileOutPutStream.close()

    }

    private fun doMkdirs() {
        val mFile = File(cacheDir, "myFile")
        if (!mFile.exists()) mFile.mkdir()
        save(mFile)

        val txtFile = File(cacheDir, "txt${File.separator}aaa.txt")
        if (!txtFile.exists()) txtFile.mkdir()

    }

    private fun save(mFile: File) {
        val data = "hello world"

        return
        val fileInputStream = FileInputStream(data)

        val fileOutputStream = FileOutputStream(mFile)

        //批量读取字节
        var length = 0

        val buf = ByteArray(8 * 1024)


    }

    fun calculateInSampleSize(options: BitmapFactory.Options, dstWidth: Int, dstHeight: Int): Int {
        val rawHeight = options.outHeight
        val rawWidth = options.outWidth
        var inSampleSize = 1
        if (rawWidth > dstWidth || rawHeight > dstHeight) {
            val widthSampleSize = rawWidth / dstWidth
            val heightSampleSize = rawHeight / dstHeight
            inSampleSize = min(widthSampleSize, heightSampleSize)
        }
        return inSampleSize
    }


    private var mCanvas: Canvas? = null

    private fun decodeStart() {
        val bitmapOption = BitmapFactory.Options()
        bitmapOption.inJustDecodeBounds = true
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.beach, bitmapOption)//.copy(Bitmap.Config.RGB_565,true)
        LogUtils.showLog("BitmapFactory", "Bitmap real " + (bitmap == null) + " w&h " + bitmapOption.outHeight + " " + bitmapOption.outWidth + " " + bitmapOption.outMimeType)

        val targetSample = calculateInSampleSize(bitmapOption, decodeIV.width, decodeIV.height)
        bitmapOption.inSampleSize = targetSample
        bitmapOption.inJustDecodeBounds = false
        val decodedBitmap = BitmapFactory.decodeResource(resources, R.drawable.beach, bitmapOption).copy(Bitmap.Config.RGB_565, true)
        //0.33MB
        LogUtils.showLog("BitmapFactory", "Bitmap real " + decodedBitmap.byteCount + (decodedBitmap == null) + decodedBitmap.width + " " + decodedBitmap.height + " w&h " + bitmapOption.outHeight + " " + bitmapOption.outWidth + " " + bitmapOption.outMimeType)

        mCanvas = Canvas(decodedBitmap)
        decodeIV.setImageBitmap(decodedBitmap)

        myBitmap.setCanvas(mCanvas)

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