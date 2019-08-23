package com.example.bod.kotlincoroutines

/**
 *
 * @ClassName: User
 * @Description:
 * @CreateDate: 2019/7/31
 */
class User private constructor(val name: String) {

    //工厂方法代替从构造函数
    companion object {
        fun newUser(email: String) = User(email)
        fun newFaceBook(faceBook: Int) = User(faceBook.toString())
    }

    fun test() {}

}