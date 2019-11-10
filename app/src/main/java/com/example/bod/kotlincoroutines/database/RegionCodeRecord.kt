package com.example.bod.kotlincoroutines.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @ClassName: RegionCodeRecord
 * @Description:
 * @CreateDate: 2019/11/8
 */
@Entity
data class RegionCodeRecord(

        @PrimaryKey val provinceCode:Int,

        @ColumnInfo(name = "name") val name:String

//        @ColumnInfo(name = "city_code") val cityCode:Int,
//        @ColumnInfo(name = "city_name") val cityName:String
)