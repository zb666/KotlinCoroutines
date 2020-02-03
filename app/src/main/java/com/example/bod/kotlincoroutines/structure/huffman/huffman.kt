package com.example.bod.kotlincoroutines.structure.huffman

import com.example.bod.kotlincoroutines.utils.log
import java.util.*
import kotlin.math.min

/**
 *
 * @ClassName: huffman
 * @Description: 哈夫曼贪心算法
 * @CreateDate: 2020/2/2
 */
fun main(){
  TreeSet<String>().apply {
      add("2")
      add("3")
      add("1")
  }.forEach {
      log("Tree Result: $it")
  }
}

