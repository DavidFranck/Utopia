package com.david.leetcode;

/**
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Question206 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
//        ListNode h = new Solution1().reverseList(head);
        ListNode h1 = new Solution2().reverseList(head);
        System.out.println("-------");
    }

    static public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        ListNode(int x, ListNode next) {
            val = x;
            this.next = next;
        }
    }

    //遍历法
    static class Solution1 {
        public ListNode reverseList(ListNode head) {
            if (head == null) return null;
            //存上一个节点把他放在cur.next
            ListNode pre = null;
            ListNode cur = head;
            while (cur != null) {
                //取出下一个节点
                ListNode oldNext = cur.next;
                //把上一个节点放在当前节点next 形成倒序效果
                cur.next = pre;
                //把当前节点存到pre中 供下次循环使用
                pre = cur;
                //迭代条件
                cur = oldNext;
            }
            return pre;
        }
    }

    //递归法 思想是先递归到最后一个元素 next =pre
    static class Solution2 {
        public ListNode reverseList(ListNode head) {
            //递归终止条件是当前为空，或者下一个节点为空
            if (head == null || head.next == null) {
                return head;
            }
            //这里的cur就是最后一个节点
            ListNode cur = reverseList(head.next);
            //这里请配合动画演示理解
            //如果链表是 1->2->3->4->5，那么此时的cur就是5
            //而head是4，head的下一个是5，下下一个是空
            //所以head.next.next 就是5->4
            head.next.next = head;
            //防止链表循环，需要将head.next设置为空
            head.next = null;
            //每层递归函数都返回cur，也就是最后一个节点
            return cur;
        }
    }
}
