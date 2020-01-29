package com.example.bod.kotlincoroutines.structure.linked;

/**
 * @ClassName: LNode
 * @Description:
 * @CreateDate: 2020/1/29
 */
public class LNode {
    //保存当前节点的元素和下一个节点的指针
    public int data;
    public LNode next;

    public LNode(int data) {
        this.data = data;
    }
}
