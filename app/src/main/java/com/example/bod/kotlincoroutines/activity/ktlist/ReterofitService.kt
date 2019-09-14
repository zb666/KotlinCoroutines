package com.example.bod.kotlincoroutines.activity.ktlist

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

/**
 *
 * @ClassName: ReterofitService
 * @Description:
 * @CreateDate: 2019/9/14
 */
class ReterofitService private constructor(){

    fun <S> createService(service:Class<S>):S{
        return retrofit.create(service)
    }

    companion object{
        val retrofit by lazy {
            Retrofit.Builder()
                    .baseUrl(IWanService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }

    }


}