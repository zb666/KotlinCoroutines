package com.example.bod.kotlincoroutines.structure

/**
 * @ClassName: NodeLinked
 * @Description:
 * @CreateDate: 2020/1/29
 */
class Node {
    internal var code: Int = 0
    internal var next: Node? = null
}


class DoubleNode<E> {
    var item: E? = null
    var preNode: DoubleNode<E>? = null
    var nextNode: DoubleNode<E>? = null
}