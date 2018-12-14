package com.david.gupao.list;

public class ListNode {
    //归并排序 先二分 在分别排序  在合并
    public static ListNode sort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = mid(head);
        ListNode right = mid.next;
        mid.next = null;

        ListNode listNode = merge1(sort(mid), sort(right));
        return listNode;
    }

    //合并两个有序链表从小到大 递归
    public static ListNode merge1(ListNode head1, ListNode head2) {
        if (head1 == null && head2 == null) {
            return null;
        }
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        ListNode head;//新的头结点
        if (head1.value > head2.value) {//小的在前面
            head = head2;
            head.next = merge1(head1, head2.next);//递归吧后面节点的next赋值
        } else {
            head = head1;
            head.next = merge1(head1.next, head2);
        }
        return head;
    }

    //合并两个有序链表从小到大 非递归
    public static ListNode merge2(ListNode head1, ListNode head2) {
        if (head1 == null && head2 == null) {
            return null;
        }
        ListNode head = head1.value < head2.value ? head1 : head2;//取小的放在head
        ListNode cur1 = head == head1 ? head1 : head2;//被合并链表 初始值为head
        ListNode cur2 = head == head1 ? head2 : head1;//合并链表

        ListNode pre = null;//curr1前一个
        ListNode next = null;//curr2后一个

        while (cur1 != null && cur2 != null) {
            //第一个必定是cur1
            if (cur1.value <= cur2.value) {
                pre = cur1;
                cur1 = cur1.next;
            } else {
                next = cur2.next;//保存cur2下一个
                pre.next = cur2;//填充cur2到cur1
                cur2.next = cur1;//链表插入
                pre = cur2;
                cur2 = next;//遍历
            }
        }
        //最后一个
        pre.next = cur1 == null ? cur2 : cur1;
        return head;
    }

    //遍历
    public static void traverse(ListNode head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    //反转
    public static ListNode reverse(ListNode head) {
        ListNode pre = null;//当前节点的上一个
        ListNode next;//当前节点的下一个节点
        while (head != null) {
            next = head.next;
            head.next = pre;//反转链表
            pre = head;//把下个元素的前一个元素记录
            head = next;//迭代遍历
        }
        return pre;
    }
    //中间节点

    public static ListNode mid(ListNode head) {
        if (head == null) return head;
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public int value;
    public ListNode next;

    public ListNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "value=" + value +
                '}';
    }
}
