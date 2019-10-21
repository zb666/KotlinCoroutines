package com.example.bod.kotlincoroutines.fx

import org.junit.Test
import java.util.ArrayList

/**
 *
 * @ClassName: TestClient
 * @Description:
 * out-> extends
 * in->Super
 * @CreateDate: 2019/10/21
 */
class TestClient {

    @Test
    fun testPerson() {

//        showA(LocalTest<Person>())


    }

    //逆变->Super
    fun showA(test: ArrayList<in Woman>) { //super只能写
        //in -> Super 消费 本身 或者高于其的父类
        test.get(0) //-> Any类型
        test.add(Woman())
        test.add(WomanStub()) //但是可写
        val get = test.get(0)
//        test.add(1)
    }

    fun showB(test:ArrayList<out Woman>){ // extend只能读
        test.get(0)
//        test.add(Woman()) 编译器报错 不能添加
    }

}