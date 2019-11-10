package com.example.bod.kotlincoroutines.activity.neteaseui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.room.Room
import com.blankj.utilcode.util.Utils
import com.example.bod.kotlincoroutines.R
import com.example.bod.kotlincoroutines.activity.BaseActivity
import com.example.bod.kotlincoroutines.database.RegionCodeRecord
import com.example.bod.kotlincoroutines.database.RegionDatabase
import com.example.bod.kotlincoroutines.database.RegionRecordDao
import com.example.bod.kotlincoroutines.fm.TestFragment
import kotlinx.android.synthetic.main.activity_re_start.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class ReStartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_re_start)

        tv_insert.setOnClickListener {
            insertData()
        }

        tv_queryall.setOnClickListener {
                queryAll()
        }

        viewPager.adapter = MyAdapter(supportFragmentManager)

    }

    var regionList: RegionRecordDao?=null

    private fun insertData() {
        launch {
            val databaseBuilder = Room.databaseBuilder(Utils.getApp(), RegionDatabase::class.java, "RegionCode").build()
            regionList = databaseBuilder.saveRegionList()
            withContext(Dispatchers.IO){
                val mapRegionList = (0 until 10).map {
                    RegionCodeRecord(
                            provinceCode = it,
                            name = "哈哈哈 $it"
                    )
                }
                regionList?.insertOrUpdate(mapRegionList[0])
            }
        }
    }

    private fun queryAll(){
        launch {
            withContext(Dispatchers.Default) {
                val regionCodeRecord = regionList?.getAll()
                Timber.d("Region: ${regionCodeRecord.toString()}")
            }
        }


    }

    private fun delete(){
        launch {
            withContext(Dispatchers.Default) {
//                val regionCodeRecord = regionList?.delete()
//                Timber.d("Region: ${regionCodeRecord.toString()}")
            }
        }
    }


    class MyAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return TestFragment()
        }

        override fun getCount(): Int {
            return  2
        }

    }
}
