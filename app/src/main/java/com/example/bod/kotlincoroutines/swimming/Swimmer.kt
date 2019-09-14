package com.example.bod.kotlincoroutines.swimming

/**
 *
 * @ClassName: Swimmer
 * @Description:
 * @CreateDate: 2019/9/3
 */
fun main() {

    //lamder的时候 单参数可以省略
    val swimmer = Swimmer{ freeStyle()}
    swimmer.swim()
}


class Swimmer(val swimming: () -> Unit) {

    fun swim() {
        swimming()
    }

}

fun breakFast(){

}

fun freeStyle(){

}