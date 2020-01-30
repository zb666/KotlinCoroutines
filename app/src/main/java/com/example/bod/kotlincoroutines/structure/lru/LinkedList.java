package com.example.bod.kotlincoroutines.structure.lru;

/**
 * @ClassName: 单链表
 * @Description:
 * @CreateDate: 2020/1/29
 * LRU 命中则数据上移，队列满则删除
 */
public class LinkedList<T> {

    //有一个节点的时候 就能查找到所有的其他节点
    private Node list;

    //多少个节点
    private int size;

    //添加节点
    public void put(T data) {
        Node head = list;
        list = new Node(data, head);
        size++;
    }

    public void put(int index, T data) {
        checkPosIndex(index);
    }

    //删除节点
    public T remove() {
        return null;
    }

    public T remove(int index) {
        return null;
    }

    public T removeLast() {
        return null;
    }

    //修改节点
    public void set(int index, T data) {
        Node sNode = new Node(data, list);
    }

    //查询节点
    //get头部节点
    public T get() {
        return null;
    }

    public T get(int index) {
        return null;
    }

    //节点的信息
    class Node {
        T data;
        Node next;

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public void checkPosIndex(int pos) {
        if (pos > size || pos <= 0) {
            throw new IndexOutOfBoundsException("out of bounds");
        }
    }

}
