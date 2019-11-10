package com.example.bod.kotlincoroutines.database

import androidx.room.*

/**
 *
 * @ClassName: RegionRecordDao
 * @Description:
 * @CreateDate: 2019/11/8
 */
@Dao
 interface RegionRecordDao {

    @Query("SELECT * FROM REGIONCODERECORD")
    fun getAll():List<RegionCodeRecord>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(regionCodeRecord: RegionCodeRecord)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(regionList:List<RegionCodeRecord>)

   @Delete
   fun delete(regionCodeRecord: RegionCodeRecord)

}