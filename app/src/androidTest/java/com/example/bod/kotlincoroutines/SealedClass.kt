package com.example.bod.kotlincoroutines

import org.junit.Test
import timber.log.Timber

/**
 *
 * @ClassName: SealedClass
 * @Description:
 * @CreateDate: 2019/10/23
 */
class SealedClass {

    //代理和接口回调

    sealed class Fruit {
        class Apple(val name:String) : Fruit()
        class Banner(val name: String) : Fruit()
    }

    val fruitList = listOf(Fruit.Apple("Apple"), Fruit.Banner("Fruit"))

    @Test
    fun test() {
        var fruit: Fruit? = null
        fruit = Fruit.Apple("111").apply {
            Timber.d("HashCode:${hashCode()}")
        }

        Timber.d("HashCode:${fruit.hashCode()}")

        fruit = Fruit.Banner("222").apply {
            Timber.d("HashCode:${hashCode()}")
        }

        Timber.d("HashCode:${fruit.hashCode()}")

        for (fruit in fruitList) {
            testFruit(fruit)
        }

    }

    fun testFruit(fruit: Fruit) {
        when (fruit) {
            Fruit.Apple("111") -> {

            }
            Fruit.Banner("222") -> {

            }
        }
    }
}