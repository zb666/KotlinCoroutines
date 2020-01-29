package com.example.bod.kotlincoroutines.structure.linked;

/**
 * @ClassName: NodeList
 * @Description:
 * @CreateDate: 2020/1/29
 */
public class NodeList {

    public LNode head, tail;
    public int size = 0;

    public NodeList() {
        head = tail = null;
    }

    public int size() {
        return size;
    }

    public void addData(int data) {
        //目前头部的数据会放在这个节点之后
        if (tail == null) {
            LNode lCurNode = new LNode(data);
            head = tail = lCurNode;
        } else {
            //做好两件事 1数据域后移 2 指针域后移
            tail.next = new LNode(data);//数据域
            tail = tail.next;//指针域
        }
        size++;
    }

    public void showNodeList() {
        LNode lCurNode = head;
        //第一个元素的
        while (lCurNode.next != null || lCurNode == tail) {
            System.out.println("Node: " + lCurNode.data + "\n");
            lCurNode = lCurNode.next;//指针后移
        }
    }

    public void reverse() {
        if (head == null || head.next == null) return;
        LNode prev = head;
        LNode cur = head.next;
        LNode tmp;
        while (cur != null) {
            //取得后面的数据
            tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
        tail = prev;
        head.next = null;
        head = tail;
    }


}
