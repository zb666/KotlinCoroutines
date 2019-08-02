package com.example.bod.kotlincoroutines.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.net.toUri
import androidx.core.view.doOnPreDraw
import androidx.core.view.marginBottom
import androidx.core.view.marginTop
import androidx.core.view.setPadding
import com.blankj.utilcode.util.ScreenUtils
import com.example.bod.kotlincoroutines.LogUtils
import com.example.bod.kotlincoroutines.Name
import com.example.bod.kotlincoroutines.R
import com.example.bod.kotlincoroutines.User
import com.example.bod.kotlincoroutines.by.BaseImpl
import com.example.bod.kotlincoroutines.by.Derived
import com.example.bod.kotlincoroutines.by.Example
import com.example.bod.kotlincoroutines.paging.ConvertAdapter
import com.example.bod.kotlincoroutines.utils.TestUtils
import com.example.bod.kotlincoroutines.utils.printLog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.reflect.ParameterizedType

class MainActivity : BaseActivity() {

    var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listOf("1","2","3","4","5").withIndex().forEach {
            Log.d("Bob", "$it.index "+it.value)
        }

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
    }


    @SuppressLint("SetTextI18n")
    fun preKtx(){
        val context = this as Context


        "".toUri()

//        tvSync.doOnPreDraw {
//            tvSync.text = "{$it.height}"
//        }

        tvSync.setOnClickListener {
            startActivity(Intent(it.context,MotionActivity::class.java))

        }





    }

    fun testAction(action:(String)->Int):Int{
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

        tvAsync.text ="" +type+ "  "+typeTwo

        Log.d("Bob", randomValue + " "+type+" "+typeTwo)
    }


    fun toSet(){
        listOf("1","2","3").toSet()
        mapOf("1" to 1,"2" to 2).any {
            it.key.equals("1")
        }

        val flatten = listOf(listOf("1"), listOf("2"), listOf("3"))

        listOf("1","2","3").filter { it == "2" }.takeIf {
            it.isNotEmpty()
        }?.random()
    }

    fun flatMap(){
        //这里多了Map过程
        val flatMap = listOf("1", "2", "4").flatMap {
            it.toMutableList()
        }
        Log.d("Bob",flatMap.toString())


        //这里没有map过程
        val flatten = listOf(listOf("1"), listOf("22"), listOf("3")).flatten()
        Log.d("Bob",flatten.toString())

        ScreenUtils.getAppScreenHeight()

    }

    fun arrayTest(){
        val intArray = intArrayOf(1, 2, 3)
        val mulArray = arrayOf("1", 1, "2", 2, "3")
    }



}
