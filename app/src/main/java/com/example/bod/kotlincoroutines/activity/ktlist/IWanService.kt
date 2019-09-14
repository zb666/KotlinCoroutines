package com.example.bod.kotlincoroutines.activity.ktlist

import retrofit2.http.GET

/**
 *
 * @ClassName: IWanService
 * @Description:
 * @CreateDate: 2019/9/14
 */
interface IWanService {

    companion object {
        const val BASE_URL =  "https://www.wanandroid.com"
    }

    @GET("/banner/json")
    suspend fun getBannerJson():Banner

}