package com.example.bod.kotlincoroutines.structure.linked;

/**
 * @ClassName: NodeLinked
 * @Description:
 * @CreateDate: 2020/1/29
 */
public class NodeLinked {

    static class Node<E>{
        E item;
        Node<E> next;//指向下一条Item
        Node<E> prev;//指向上一条Item

        public Node(E item, Node<E> next, Node<E> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}
