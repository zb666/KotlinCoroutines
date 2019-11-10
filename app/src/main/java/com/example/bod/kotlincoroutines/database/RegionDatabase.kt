package com.example.bod.kotlincoroutines.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 *
 * @ClassName: RegionDatabase
 * @Description:
 * @CreateDate: 2019/11/8
 */
@Database(entities = [RegionCodeRecord::class],version = 1)
abstract class RegionDatabase: RoomDatabase() {

    abstract fun saveRegionList():RegionRecordDao

}