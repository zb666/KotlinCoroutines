package com.example.bod.kotlincoroutines.structure.review;

import com.example.bod.kotlincoroutines.structure.Node;

/**
 * @ClassName: LinkedListDemo
 * @Description:
 * @CreateDate: 2020/2/2
 */
public class LinkedListDemo {
    //初始化数据  为Head
    LinkNode linkList;
    int size;

    void put(int data){
        LinkNode head = this.linkList;
        linkList = new LinkNode(data,head);
        size++;
    }

    void put(int index,int data){
        LinkNode cur = linkList;
        LinkNode head = linkList;
        for (int i = 0;i<index;i++){
            head = cur;
            cur = cur.linkNode;
        }
        head.linkNode = new LinkNode(data,cur);
        size++;
    }

}
