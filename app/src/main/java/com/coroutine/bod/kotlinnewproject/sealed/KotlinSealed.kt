package com.coroutine.bod.kotlinnewproject.sealed

sealed class KotlinSealed {
    class SealedA():KotlinSealed()
    class SealedB():KotlinSealed()
    class SealedC():KotlinSealed()
    fun printHello(){
        print("Hello $this")
    }
}