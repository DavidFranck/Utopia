package com.david.list;

import org.junit.Test;

import static com.david.list.ListNode.merge1;
import static com.david.list.ListNode.merge2;

public class TestLinkedList {

    @Test
    public void testLinkedList() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);

        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);


//        listNode1.next = listNode2;
//        listNode2.next = listNode3;
//        listNode5.next = listNode4;

        listNode1.next = listNode3;
        listNode2.next = listNode5;

        ListNode head = listNode1;
        ListNode head2 = listNode2;


//        ListNode.traverse(head);
//        head = ListNode.reverse(head);//反转后3位头结点
//        ListNode.traverse(head);
//        System.out.println(ListNode.mid(head));


        ListNode listNode = merge2(head, head2);
        ListNode.traverse(listNode);
    }
}
