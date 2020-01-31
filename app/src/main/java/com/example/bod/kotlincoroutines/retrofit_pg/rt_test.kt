package com.example.bod.kotlincoroutines.retrofit_pg

import retrofit2.Retrofit

/**
 *
 * @ClassName: rt_test
 * @Description:
 * @CreateDate: 2020/1/31
 */
fun main(){
//    Retrofit.Builder()
//            .baseUrl("")
//            .addConverterFactory()

    factory(BobConvert())
}

fun factory(convert:IConvert.IFactory) = convert.createConvert()

class BobConvert:IConvert.IFactory(){

    override fun createConvert(): IConvert {
        return Bob()
    }

    override fun add(): String {
        return "Add Success"
    }

}

class Bob:IConvert



