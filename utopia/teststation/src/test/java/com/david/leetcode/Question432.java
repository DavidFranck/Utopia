package com.david.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 请你实现一个数据结构支持以下操作：
 * <p>
 * Inc(key) - 插入一个新的值为 1 的 key。或者使一个存在的 key 增加一，保证 key 不为空字符串。
 * Dec(key) - 如果这个 key 的值是 1，那么把他从数据结构中移除掉。否则使一个存在的 key 值减一。如果这个 key 不存在，这个函数不做任何事情。key 保证不为空字符串。
 * GetMaxKey() - 返回 key 中值最大的任意一个。如果没有元素存在，返回一个空字符串"" 。
 * GetMinKey() - 返回 key 中值最小的任意一个。如果没有元素存在，返回一个空字符串""。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/all-oone-data-structure
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * <p>
 * 使用hashmap存储node 使node查找可以n(1)
 * 使用双向链表存储数据大小值 desc向头部移动 inc向尾部移动 类似lru
 */
public class Question432 {
    public static void main(String[] args) {
//        String k1 = "k1";
//        String k2 = "k2";
//        AllOne obj = new AllOne();
//        obj.inc(k1);
//        obj.inc(k2);
//        obj.inc(k2);
//        System.out.println("max key:" + obj.getMaxKey());
//        System.out.println("min key:" + obj.getMinKey());
//        obj.dec(k1);
//        System.out.println("max key:" + obj.getMaxKey());
//        System.out.println("min key:" + obj.getMinKey());
//        obj.dec(k2);
//        obj.dec(k2);
//        System.out.println("max key:" + obj.getMaxKey());
//        System.out.println("min key:" + obj.getMinKey());

        Solution1 obj = new Solution1();
        obj.inc("a");
        obj.inc("b");
        obj.inc("c");
        obj.inc("d");

        obj.inc("a");
        obj.inc("b");
        obj.inc("c");
        obj.inc("d");

        obj.inc("c");
        obj.inc("d");
        obj.inc("d");
        obj.inc("a");
        System.out.println("max key:" + obj.getMaxKey());
        System.out.println("min key:" + obj.getMinKey());
    }

    /**
     * 解法1
     */
    static class Solution2 {
        class Node {
            Node pre, next;
            int val;
            ArrayList<String> content;

            public Node(int val, boolean isData) {
                this.val = val;
                if (isData) this.content = new ArrayList<>();
            }
        }

        class DoubleLinkedList {
            Node head, tail;

            public DoubleLinkedList() {
                head = new Node(0, false);
                tail = new Node(0, false);
                head.next = tail;
                tail.pre = head;
            }

            /**
             * 在base前插入节点
             *
             * @param base
             * @param node
             */
            public void insertNodeAtPre(Node base, Node node) {
                node.pre = base.pre;
                base.pre.next = node;
                node.next = base;
                base.pre = node;
            }

            /**
             * 在base后插入节点
             *
             * @param base
             * @param node
             */
            public void insertNodeAtNext(Node base, Node node) {
                node.next = base.next;
                base.next.pre = node;
                base.next = node;
                node.pre = base;
            }

            public void deleteNode(Node node) {
                node.pre.next = node.next;
                node.next.pre = node.pre;
            }

            public void deleteTail() {
                tail.pre = tail.pre.pre;
                tail.pre.next = tail;
            }
        }

        HashMap<String, Node> dict;
        DoubleLinkedList list;


        public Solution2() {
            dict = new HashMap<>();
            list = new DoubleLinkedList();
        }

        /**
         * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
         */
        public void inc(String key) {
            int num = 0;
            if (dict.containsKey(key)) {
                num = dict.get(key).val;
                Node base = dict.get(key);
                if (base.content.size() == 1 && base.next.val != num + 1) {
                    base.val = num + 1;
                } else {
                    base.content.remove(key);
                    if (base.next.val == num + 1) {
                        base.next.content.add(key);
                        dict.put(key, base.next);
                    } else {
                        Node node = new Node(num + 1, true);
                        node.content.add(key);
                        list.insertNodeAtNext(base, node);
                        dict.put(key, node);
                    }
                    if (base.content.isEmpty()) list.deleteNode(base);
                }
            } else {
                if (list.head.next.val == 1) {
                    dict.put(key, list.head.next);
                    list.head.next.content.add(key);
                } else {
                    Node node = new Node(num + 1, true);
                    dict.put(key, node);
                    node.content.add(key);
                    list.insertNodeAtNext(list.head, node);
                }
            }
        }

        /**
         * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
         */
        public void dec(String key) {
            if (!dict.containsKey(key)) return;
            Node base = dict.get(key);
            int num = base.val;
            if (base.content.size() == 1) {
                num--;
                if (num == 0) {
                    list.deleteNode(base);
                    dict.remove(key);
                } else {
                    if (base.pre.val == num) {
                        base.pre.content.add(key);
                        list.deleteNode(base);
                        dict.put(key, base.pre);
                    } else {
                        base.val = num;
                    }
                }
            } else {
                base.content.remove(key);
                num--;
                if (num == 0) {
                    dict.remove(key);
                } else {
                    if (base.pre.val == num) {
                        base.pre.content.add(key);
                        dict.put(key, base.pre);
                    } else {
                        Node node = new Node(num, true);
                        node.content.add(key);
                        list.insertNodeAtPre(base, node);
                        dict.put(key, node);
                    }
                }

            }
        }

        /**
         * Returns one of the keys with maximal value.
         */
        public String getMaxKey() {
            return list.tail.pre.content == null ? "" : list.tail.pre.content.get(0);
        }

        /**
         * Returns one of the keys with Minimal value.
         */
        public String getMinKey() {
            return list.head.next.content == null ? "" : list.head.next.content.get(0);
        }

    }

    /**
     * 解法1
     */
    static class Solution1 {

        private Node head;
        private Node tail;
        private Map<String, Node> map;

        /**
         * Initialize your data structure here.
         */
        public Solution1() {
            //构建 head<--->tail 双向链表 前小后大
            head = new Node("head", -1);
            tail = new Node("tail", -1);
            head.next = tail;
            tail.prev = head;
            map = new HashMap<>();
        }

        /**
         * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
         */
        public void inc(String key) {
            map.merge(key, new Node(key, 1), (node1, node2) -> {
                node1.value++;
                return node1;
            });
            map.get(key).moveToTail(head, tail);
        }

        /**
         * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
         */
        public void dec(String key) {
            Node temp = map.get(key);
            // 如果你在函数式接口里面返回了null，那computeIfPresent函数会将该key删除（当前前提是该key存在）
            map.computeIfPresent(key, (k, node) -> {
                node.value--;
                if (node.value == 0) {
                    return null;
                }
                return node;
            });
            //节点存在移动到头部
            if (map.containsKey(key)) {
                map.get(key).moveToHead(head);
            } else if (temp != null) {//减为0 链表中删除元素
                temp.delete();
            }
        }

        /**
         * Returns one of the keys with maximal value.
         */
        public String getMaxKey() {
            if (map.isEmpty()) {
                return "";
            } else {
                return tail.prev.key;
            }
        }

        /**
         * Returns one of the keys with Minimal value.
         */
        public String getMinKey() {
            if (map.isEmpty()) {
                return "";
            } else {
                return head.next.key;
            }
        }

        static class Node {

            String key;
            int value;
            Node prev;
            Node next;

            public Node(String key, int value) {
                this.key = key;
                this.value = value;
                prev = null;
                next = null;
            }

            //当前值大的话逐步向链表尾部移动
            public void moveToTail(Node head, Node tail) {
                if (prev == next && prev == null) {
                    next = head.next;
                    prev = head;
                    next.prev = this;
                    head.next = this;
                } else if (next != tail) {
                    while (value > next.value && next != tail) {
                        prev.next = next;
                        next.prev = prev;
                        Node temp = next;
                        next = temp.next;
                        temp.next = this;
                        prev = temp;
                        next.prev = this;
                    }
                }
            }

            //当前节点值比prev节点小的话 将当前节点向移动到prev前面
            public void moveToHead(Node head) {
                if (prev != head && value < prev.value) {
                    prev.next = next;
                    next.prev = prev;
                    Node temp = prev;
                    next = temp;
                    prev = temp.prev;
                    prev.next = this;
                    temp.prev = this;
                }
            }

            //删除节点
            public void delete() {
                prev.next = next;
                next.prev = prev;
                prev = null;
                next = null;
            }
        }
    }


/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
}
