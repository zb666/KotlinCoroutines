package com.example.bod.kotlincoroutines.activity

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.core.net.toUri
import com.blankj.utilcode.util.ScreenUtils
import com.example.bod.kotlincoroutines.*
import com.example.bod.kotlincoroutines.by.BaseImpl
import com.example.bod.kotlincoroutines.by.Derived
import com.example.bod.kotlincoroutines.by.Example
import com.example.bod.kotlincoroutines.download.AppUpdate
import com.example.bod.kotlincoroutines.download.DownloadBean
import com.example.bod.kotlincoroutines.download.INetCallback
import com.example.bod.kotlincoroutines.paging.ConvertAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.io.File
import java.lang.reflect.ParameterizedType
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : BaseActivity() {

    var mediaPlayer: MediaPlayer? = null

    val updateUrl = "http://59.110.162.30/v450_imooc_updater.apk"

    var mStatus = 0x0000001

    private val MODE_SHIFT = 30

    private val MODE_MASK = 0x3 shl MODE_SHIFT

    val resUrl = "https://resources-en.brainco.cn/now/course/train/meditation/lrc.txt"

    val listEle = arrayListOf<String>()

    val ss = "sss"

    val toSetList = arrayListOf(1, 2, 5, 4, 3, 4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val pair = 1 to "zhobo"
        pair.second

        val find = listEle.find {
            it.isNotEmpty()
        }



        listEle.map {

        }

        listOf("1", "2", "3", "4", "5").withIndex().forEach {
            Log.d("Bob", "$it.index " + it.value)
        }


        tvBottom.text = """
            First
            Second
            Third
        """.trimIndent()


        val flattenList = listOf(listOf("1"), listOf("2")).flatMap {
            listOf(it) //合并集合元素
        }.flatten()
        //flatten 平铺集合元素

        tvBottom.text = "${SysZhVerifyUtil.isZh()}+${Locale.getDefault().language}"

//        val i = 1 or 2
//        1 xor 2

        val kProperty1 = User::name
        val kFunction1 = User::test

        val faceBookUser = User.newFaceBook(10)

        val userTest = User.newUser("email")

        flatMap()


        val testAction = testAction {
            hashCode()
        }

        var derived = Derived(BaseImpl("baseName"))
        derived.print()

        var example = Example()
        val str = example.str

        preKtx()


        generateClick()

//        tvAsync.text = "Beginner Congratulations! You first-time log into Focus Now!"
//                .toSpannable()
//                .apply {
//                    setSpan(ForegroundColorSpan(0xFF457CFD.toInt()), 49, length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
//                }

        //https://resources-en.brainco.cn/now/course/train/meditation/lrc.txt
        val pathFile = resUrl.substring(resUrl.indexOf("courses") + "courses".length + 1, resUrl.length).replace("/", "_")
        val cacheFile = pathFile.cacheFile()

        tvAsync.text = cacheFile.absolutePath

        tvAsync.text = resources.getString(R.string.name_title, "Chionese", 2)

        var strBuild = StringBuilder()
        for (index in 1..10) {
            strBuild.append("$index")
        }
        tvBottom.text = strBuild

        tvBottom.setOnClickListener {
            //            myViewGroup.invalidate()
            startActivity(Intent(it.context, AsyncInflateActivity::class.java))
        }

        val name = Name("")
        launch {
            name.getToken("MyToken") {
                tvBottom.text = it.length.toString()
                it.length
            }
        }

        val strPair = 1 to "str"
        val sb = StringBuilder()
        val map = arrayOf(1_000, 2_000, 3_000).asSequence().map {
            it
        }.filter {
            it > 0
        }.forEach {
            sb.append(it)
        }

        tvSync.text = sb



        listOf("abc", "def").flatMap {
            it.toList()
        }.let {
            //            tvSync.text = it
        }

        tvSync.text = listOf(listOf("1", "2"), listOf("3", "4"))
                .flatten().toString()

        val toSet = listOf("1", "2", "3").toSet()

        val listPerson = ArrayList<User>()

        (1..100).forEach {
            listPerson.add(User.newUser("$it"))
        }

        listPerson.asSequence().filter {
            it.name.length > 1 //Filter first 有利于 减少map 变化的开销
        }.map {
            it.name
        }.toList().let {
            tvSync.text = it.toString()
        }

        var aaa = StringBuilder()
        (1..20).asSequence()
                .filter {
                    it % 2 == 0 && it > 15
                }.map {
                    "s $it "
                }.toList().forEach {
                    aaa.append(it).let {
                        tvAsync.text = it
                    }
                }

        //any 任意一个满足
        //all 需要全部满足

        arrayOf(1, 2, 3).all {
            it < 2
        }.let {
            tvAsync.text = "$it"
        }
//        tvSync.text = mutableListOf("111",111,true).toString()

        mapOf(1 to "a", 2 to "b", 3 to "c").forEach {
            LogUtils.showLog("MapTest", "${it.key} ${it.value}")
        }

        ValueAnimator.ofFloat(0f, 1f)
                .apply {
                    addUpdateListener {
                        LogUtils.showLog("animatedValue", "${it.animatedValue}")

                    }
                }.start()


        tvAsync.text = "安装"
        tvAsync.setOnClickListener {
            //MD5 可以用于文件下载过程中防止被篡改和数据丢失
            startDownload()
        }

        launch {
            delay(100)
            tvBottom.text = toSetList.toSet().toString()
        }

        val map1 = toSetList.map {
            it.hashCode()
        }


        val foo = { x: Int -> val y = x + 1 }

        val mList = arrayListOf<Body>()
        (0 until 10).forEach {
            mList.add(Body("aaa",age = it))
        }

        val list = listOf(1..20,2..15,3..10)
        //集合中元素的组合 并且做变化
        val listStr = list.flatMap {
            it.map {
                "字符串$it"
            }
        }
        //合并集合元素成为一个集合
        listStr.forEach {
            LogUtils.showLog("BobList",it)
        }


    }

    private fun checkUpdate() {
        AppUpdate.getInstance().checkUpdate("http://www.52res.cn/appupdate/update.json", this, object : INetCallback {
            override fun failed(throwable: Throwable?) {
                LogUtils.showLog("BobUpgrade", throwable?.toString() ?: "")
            }

            override fun success(response: String?) {
                LogUtils.showLog("BobUpgrade", DownloadBean.parse(response)!!.toString())
            }

        })
    }

    private fun startDownload() {
//        val file = File(cacheDir,"zhy")
//        if (!file.exists()){
//            file.mkdir()
//        }

        //开始安装
        AppUpdate.getInstance().startDonwload(this, updateUrl)

    }

    private fun String.cacheFile(): File {
        return File(cacheDir.path + File.separator + this)
    }


    @SuppressLint("SetTextI18n")
    fun preKtx() {
        val context = this as Context


        "".toUri()

//        tvSync.doOnPreDraw {
//            tvSync.text = "{$it.height}"
//        }

        tvSync.setOnClickListener {
            startActivity(Intent(it.context, MotionActivity::class.java))

        }


    }

    fun testAction(action: (String) -> Int): Int {
        return action("1")
    }

    private fun generateClick() {
        val randomValue = listOf("1", "2", "3", "5", "5", "2")
                .filter {
                    it.toInt() > 6
                }.takeIf {
                    it.isNotEmpty()
                }?.random()


        val paramType = ConvertAdapter::class.java.genericSuperclass as ParameterizedType
        val typeArray = paramType.actualTypeArguments
        val type = typeArray[0]
        val typeTwo = typeArray[1]

//        tvAsync.text = "" + type + "  " + typeTwo
        tvAsync.text = resources.getString(R.string.name_title, "Chionese", 2)

        Log.d("Bob", randomValue + " " + type + " " + typeTwo)

        try {
            1 / 0
            "".substring(0, 3)
        } catch (ex: Throwable) {
            val exception = ex
            val exception1 = ex
        }

        launch(context = Dispatchers.Main) {
            val result = withContext(context = Dispatchers.IO) {
                delay(10000)
                "result ${Thread.currentThread().name}"
            }
            LogUtils.showLog("BobAwait", Thread.currentThread().name + result)
        }

        viewModelStore.clear()


    }


    fun toSet() {
        listOf("1", "2", "3").toSet()
        mapOf("1" to 1, "2" to 2).any {
            it.key.equals("1")
        }

        val flatten = listOf(listOf("1"), listOf("2"), listOf("3"))

        listOf("1", "2", "3").filter { it == "2" }.takeIf {
            it.isNotEmpty()
        }?.random()
    }

    fun flatMap() {
        //这里多了Map过程
        val flatMap = listOf("1", "2", "4").flatMap {
            it.toMutableList()
        }
        Log.d("Bob", flatMap.toString())


        //这里没有map过程
        val flatten = listOf(listOf("1"), listOf("22"), listOf("3")).flatten()
        Log.d("Bob", flatten.toString())

        ScreenUtils.getAppScreenHeight()

    }

    fun arrayTest() {
        val intArray = intArrayOf(1, 2, 3)
        val mulArray = arrayOf("1", 1, "2", 2, "3")
        test {
            this
        }
    }


    fun test(action: (String) -> Activity) {
        action("").isFinishing
    }

    suspend fun String.loadCache(): File {
        return coroutineScope {
            withContext(Dispatchers.IO) {
                File("$this")
            }
        }
    }


}
