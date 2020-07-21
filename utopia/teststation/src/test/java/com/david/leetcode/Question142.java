package com.david.leetcode;

import java.util.HashSet;
import java.util.Objects;

public class Question142 {
    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(0);
        ListNode n3 = new ListNode(4);
        head.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n1;

//        ListNode res = new Solution1().detectCycle(head);
        ListNode res = new Solution2().detectCycle(head);
        System.out.println("-----------------");
    }


    //     Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        ListNode(int x, ListNode next) {
            this.val = x;
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ListNode node = (ListNode) o;
            return val == node.val;
        }

        @Override
        public int hashCode() {
            return Objects.hash(val);
        }
    }

    /*
        取第一个重复的节点就是环的头节点 使用map去重
        https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/linked-list-cycle-ii-kuai-man-zhi-zhen-shuang-zhi-/
     */
    static class Solution1 {
        public ListNode detectCycle(ListNode head) {
            HashSet<ListNode> visited = new HashSet<>();
            //保证head不变
            ListNode n = head;
            while (n != null) {
                if (visited.contains(n)) return n;
                visited.add(n);
                n = n.next;
            }
            return null;
        }
    }

    /*
         双指针算法
         f:快捷点
         s:慢节点
         n:圈数
         a:环之前的长度
         b:环的长度
         k: 环入口节点的位置

        第一次相遇：
        快节点路程是慢节点的2倍
        f=2s
        f=s+nb
        ==> f=nb 慢节点跑了n圈

        第二次相遇:
        k=a+nb
        ==> s(已经走了nb)向前走a即可到环入口
        ==> f 指向head也向前走a 相遇的时候既环入口
     */
    static class Solution2 {
        public ListNode detectCycle(ListNode head) {
            ListNode f = head, s = head;
            //第一次相遇
            do {
                if (f == null || f.next == null) return null;//遍历到末尾没有换节点
                f = f.next.next;
                s = s.next;
            } while (!Objects.equals(f, s));
            //第二次相遇
            f = head;//fast节点置为head
            while (!Objects.equals(f, s)) {
                f = f.next;
                s = s.next;
            }
            return s;
        }
    }
}
