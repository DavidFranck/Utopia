package com.david.leetcode;

import java.util.Stack;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-stack
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 题解
 * https://leetcode-cn.com/problems/min-stack/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-38/
 */
public class Question155 {
    public static void main(String[] args) {

    }

    //链表每个node存储当前最小值
    static class Solution2 {
        static class Node {
            int value;
            Node next;
            int min;

            public Node(int value, int min) {
                this.value = value;
                this.min = min;
            }
        }

        private Node top;

        public Solution2() {

        }

        public void push(int x) {
            //为空的时候最小值即为自己
            if (top == null) {
                top = new Node(x, x);
            } else {
                //新的node 放在top位置 min取跟上一个min的最小值(即当前最小值)
                Node node = new Node(x, Math.min(x, top.min));
                node.next = top;
                top = node;
            }
        }

        public void pop() {
            //没有元素直接返回
            if (top == null) return;
            //只有一个元素时候删除当前元素
            if (top.next == null) {
                top = null;
            } else {
                //删除当前元素
                Node node = top.next;
                top.next = null;
                top = node;
            }
        }

        public int top() {
            return top.value;
        }

        public int getMin() {
            return top.min;
        }

    }

    //使用辅助栈存储最小值
    static class Solution1 {
        private Stack<Integer> stack;
        private Stack<Integer> minStack;

        /**
         * initialize your data structure here.
         */
        public Solution1() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int x) {
            stack.push(x);
            //最小栈空的时候直接放入 它就是最小值
            if (minStack.isEmpty()) {
                minStack.push(x);
            } else if (minStack.peek() >= x) { //x小于等于最小值入栈
                minStack.push(x);
            }
        }

        public void pop() {
            if (stack.pop().equals(minStack.peek())) {//最小值出栈最小栈才pop
                minStack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
}
