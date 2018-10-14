package com.david.list;

import org.junit.Test;

public class TestLinkedList {

    @Test
    public void testLinkedList() {
        final ListNode listNode1 = new ListNode(1);
        final ListNode listNode2 = new ListNode(2);
        final ListNode listNode3 = new ListNode(3);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        ListNode.traverse(listNode1);
        ListNode.reverse(listNode1);//反转后3位头结点
        ListNode.traverse(listNode3);
        System.out.println(ListNode.mid(listNode3));
    }
}
