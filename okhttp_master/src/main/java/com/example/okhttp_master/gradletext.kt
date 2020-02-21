package com.example.okhttp_master

import java.io.File

/**
 *
 * @ClassName: gradletext
 * @Description:
 * @CreateDate: 2020/2/16
 */
fun main(){
    File("build.gradle").readText()
            .toCharArray()
            .filter {
                !it.isWhitespace()
            }.groupBy { it }
            .map {
                it.key to it.value.size
            }.let {
                println(it)
            }
}