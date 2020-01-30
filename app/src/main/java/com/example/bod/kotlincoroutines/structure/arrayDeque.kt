package com.example.bod.kotlincoroutines.structure

import com.example.bod.kotlincoroutines.utils.log
import java.util.*

/**
 *
 * @ClassName: arrayDeque
 * @Description:
 * @CreateDate: 2020/1/30
 */
fun main(){

    val arrayDeque = ArrayDeque<String>(3)
    arrayDeque.add("A")
    arrayDeque.add("B")
    arrayDeque.add("C")
    arrayDeque.add("F")

    arrayDeque.pop()
    arrayDeque.poll()
    arrayDeque.remove()
    arrayDeque.push("")
    arrayDeque.run {
        offer("E")
        offerFirst("J")
        offerLast("L")
    }

    for (s in arrayDeque) {
        log(s)
    }

}