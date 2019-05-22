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
        ListNode.traverse(deleteDuplication_1(listNode1));
        ListNode.traverse(deleteDuplication_2(listNode1));

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