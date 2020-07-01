package com.david.leetcode;

import java.util.LinkedList;

/**
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 * 示例 2：
 * <p>
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 * 提示：
 * <p>
 * 1 <= values <= 10000
 * 最多会对 appendTail、deleteHead 进行 10000 次调用
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer09 {
    public static void main(String[] args) {
        CQueue obj = new CQueue();
        obj.appendTail(1);
        int param_2 = obj.deleteHead();
        System.out.println(param_2);
    }

    /**
     * 链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/solution/mian-shi-ti-09-yong-liang-ge-zhan-shi-xian-dui-l-2/
     * 栈：先入后出
     * 队列：先入先出
     * 思路：从栈A 拿出导入栈B中 实现倒序 删除
     * 由两个队列完成 A队列负责加入 B队列负责将A中数据倒序灌入B然后删除
     */
    static class CQueue {
        LinkedList<Integer> A, B;

        //初始化两个栈
        public CQueue() {
            A = new LinkedList<>();
            B = new LinkedList<>();
        }

        // push A
        public void appendTail(int value) {
            A.addLast(value);
        }

        /**
         * 1. B 不为空直接pop
         * 2. A B 都为空的时候证明没有元素返回-1
         * 3. A有元素的时候 倒置放入B 在清空
         */
        public int deleteHead() {
            if (!B.isEmpty()) return B.removeLast();
            if (A.isEmpty()) return -1;
            while (!A.isEmpty()) B.addLast(A.removeLast());
            return B.removeLast();
        }
    }
}
