package com.example.bod.kotlincoroutines

/**
 * @ClassName: A
 * @Description:
 * @CreateDate: 2019/9/1
 */
class A {

    fun a() {
        val buf = ByteArray(8 * 1024)

        val audioPlay = AudioPlay(name = "Zb666")
        audioPlay.getName()

    }

    fun test() {
        val worker = Worker { "2" }
    }
}
