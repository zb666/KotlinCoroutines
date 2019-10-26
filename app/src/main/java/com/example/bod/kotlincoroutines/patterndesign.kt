package com.example.bod.kotlincoroutines

/**
 *
 * @ClassName: patterndesign
 * @Description:
 * @CreateDate: 2019/10/26
 */

fun main() {


}

interface Target {
    fun target()
}

interface Adapter {
    fun ask()
}

//构造传入 将Adapter转化成Target锁需要的方法
class A(val adapter: Adapter) : Target {
    override fun target() {
        adapter.ask()
    }
}