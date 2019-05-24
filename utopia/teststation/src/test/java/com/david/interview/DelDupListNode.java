package com.david.interview;

import com.david.gupao.list.ListNode;

/**
 * 删除重复链表元素
 */
public class DelDupListNode {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(356);
        ListNode listNode2 = new ListNode(1);
        ListNode listNode3 = new ListNode(1);

        ListNode listNode4 = new ListNode(1);
        ListNode listNode5 = new ListNode(1);
        ListNode listNode6 = new ListNode(5);


        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;


        ListNode.traverse(listNode1);
//        ListNode.traverse(deleteDuplication_1(listNode1));
//        ListNode.traverse(deleteDuplication_2(listNode1));
        ListNode.traverse(deleteDuplication_3(listNode1));

    }

    //方法三：循环实现:剑指offer解法
    public static ListNode deleteDuplication_3(ListNode pHead) {
        if (pHead == null || pHead.next == null)
            return pHead;
        ListNode pPreNode = null;//上一个节点
        ListNode pNode = pHead;//当前节点
        while (pNode != null) {
            ListNode pNext = pNode.next;//下一个节点
            boolean needDelete = false;//是否删除的标志
            //当前节点与下一个节点值相同
            if (pNext != null && pNext.value == pNode.value)
                needDelete = true;
            //不相同的时候 迭代一次
            if (!needDelete) {
                pPreNode = pNode;//更新上一个人节点
                pNode = pNode.next;//迭代
            } else {//相同的时候 迭代到不重复为止
                int value = pNode.value;
                ListNode pToBeDel = pNode;
                while (pToBeDel != null && pToBeDel.value == value) {
                    pNext = pToBeDel.next;
                    pToBeDel = pNext;
                }
                if (pPreNode == null)
                    pHead = pNext;
                else
                    pPreNode.next = pNext;
                pNode = pNext;
            }
        }
        return pHead;
    }


    // 递归清空所有重复元素
    public static ListNode deleteDuplication_2(ListNode pHead) {
        // 递归停止条件
        if (pHead == null || pHead.next == null) return pHead;
        ListNode next = pHead.next;
        // 如果pHead是重复元素 忽略掉重复元素
        if (pHead.value == next.value) {
            next = next.next;
            while (next != null && next.value == pHead.value)
                next = next.next;
            return deleteDuplication_2(next);
        } else {
            // pHead不是重复元素 组装链表
            pHead.next = deleteDuplication_2(next);
            return pHead; //返回头部元素
        }
    }


    // 递归实现
    public static ListNode deleteDuplication_1(ListNode pHead) {
        // 递归停止条件
        if (pHead == null || pHead.next == null)
            return pHead;
        ListNode current = pHead.next;
        // 如果pHead是重复元素
        if (pHead.value == current.value) {
            current = current.next;
            while (current != null && current.value == pHead.value)
                current = current.next;
            return deleteDuplication_1(current);
        } else {
            // pHead不是重复元素
            pHead.next = deleteDuplication_1(current);
            return pHead;
        }
    }
}