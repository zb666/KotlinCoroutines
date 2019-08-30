package com.example.bod.kotlincoroutines

/**
 *
 * @ClassName: Main
 * @Description:
 * @CreateDate: 2019/8/30
 */


fun main(){
    print("aaaaa\n")

    val list = listOf(0..10,1..20,2..30)

    //把集合打平 其次把里面的集合元素做map
   list.flatMap {
       it.map {
           "flatMap $it \n"
       }
   }.forEach {
       print(it)
   }

}