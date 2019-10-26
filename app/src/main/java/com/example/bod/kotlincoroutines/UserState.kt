package com.example.bod.kotlincoroutines

/**
 *
 * @ClassName: UserState
 * @Description:
 * @CreateDate: 2019/10/25
 */


sealed class UserState(val name:String,val isAuthorized:Boolean){

    abstract fun click()

    class A:UserState(name = "AAA",isAuthorized = true){
        override fun click() {

        }

    }
}