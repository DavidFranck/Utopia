package com.david;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LRUAlgorithm {
    public static void main(String[] args) {
        LRUCache<Integer, String> lru = new LRUCache<Integer, String>(3);
        lru.put(1, "a"); // 1:a
        System.out.println(lru.toString());
        lru.put(2, "b"); // 2:b 1:a
        System.out.println(lru.toString());
        lru.put(3, "c"); // 3:c 2:b 1:a
        System.out.println(lru.toString());
        lru.put(4, "d"); // 4:d 3:c 2:b
        System.out.println(lru.toString());
        lru.put(1, "aa"); // 1:aa 4:d 3:c
        System.out.println(lru.toString());
        lru.put(2, "bb"); // 2:bb 1:aa 4:d
        System.out.println(lru.toString());
        lru.put(5, "e"); // 5:e 2:bb 1:aa
        System.out.println(lru.toString());
        lru.get(1); // 1:aa 5:e 2:bb
        System.out.println(lru.toString());
        lru.remove(11); // 1:aa 5:e 2:bb
        System.out.println(lru.toString());
        lru.remove(1); //5:e 2:bb
        System.out.println(lru.toString());
        lru.put(1, "aaa"); //1:aaa 5:e 2:bb
        System.out.println(lru.toString());
    }

    @Test
    public void testLinkedHashMap() {
        LRULinkedHashMap<Integer, String> lru = new LRULinkedHashMap<Integer, String>(3);
        lru.put(1, "a"); // 1:a
        System.out.println(lru.toString());
        lru.put(2, "b"); // 2:b 1:a
        System.out.println(lru.toString());
        lru.put(3, "c"); // 3:c 2:b 1:a
        System.out.println(lru.toString());
        lru.get(1);
        System.out.println(lru.toString());
        lru.put(4,"d");
        System.out.println(lru.toString());

//        lru.put(4, "d"); // 4:d 3:c 2:b
//        System.out.println(lru.toString());
//        lru.put(1, "aa"); // 1:aa 4:d 3:c
//        System.out.println(lru.toString());
//        lru.put(2, "bb"); // 2:bb 1:aa 4:d
//        System.out.println(lru.toString());
//        lru.put(5, "e"); // 5:e 2:bb 1:aa
//        System.out.println(lru.toString());
//        lru.get(1); // 1:aa 5:e 2:bb
//        System.out.println(lru.toString());
//        lru.remove(11); // 1:aa 5:e 2:bb
//        System.out.println(lru.toString());
//        lru.remove(1); //5:e 2:bb
//        System.out.println(lru.toString());
//        lru.put(1, "aaa"); //1:aaa 5:e 2:bb
//        System.out.println(lru.toString());
    }

    /**
     * 类说明:利用LinkedHashMap实现简单的缓存, 必须实现removeEldestEntry方法,具体参见JDK文档 * * @author dennis * * @param <K> * @param <V>\
     * 由于是链表存储 所以遍历很慢 当数据量大的时候性能会降低
     */
    public static class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {
        private final int maxCapacity;
        private static final float DEFAULT_LOAD_FACTOR = 0.75f;
        private final Lock lock = new ReentrantLock();

        public LRULinkedHashMap(int maxCapacity) {
            super(maxCapacity, DEFAULT_LOAD_FACTOR, true);
            this.maxCapacity = maxCapacity;
        }

        @Override
        protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
            //长度超过缓存最大个数清除
            return size() > maxCapacity;
        }

        @Override
        public boolean containsKey(Object key) {
            try {
                lock.lock();
                return super.containsKey(key);
            } finally {
                lock.unlock();
            }
        }

        @Override
        public V get(Object key) {
            try {
                lock.lock();
                return super.get(key);
            } finally {
                lock.unlock();
            }
        }

        @Override
        public V put(K key, V value) {
            try {
                lock.lock();
                return super.put(key, value);
            } finally {
                lock.unlock();
            }
        }

        public int size() {
            try {
                lock.lock();
                return super.size();
            } finally {
                lock.unlock();
            }
        }

        public void clear() {
            try {
                lock.lock();
                super.clear();
            } finally {
                lock.unlock();
            }
        }

        public Collection<Map.Entry<K, V>> getAll() {
            try {
                lock.lock();
                return new ArrayList<Map.Entry<K, V>>(super.entrySet());
            } finally {
                lock.unlock();
            }
        }
    }


    public static class LRUCache<K, V> {
        private int currentCacheSize;
        private int CacheCapcity;
        private HashMap<K, CacheNode> caches;
        private CacheNode first;
        private CacheNode last;

        public LRUCache(int size) {
            currentCacheSize = 0;
            this.CacheCapcity = size;
            caches = new HashMap<K, CacheNode>(size);
        }

        public void put(K k, V v) {
            CacheNode node = caches.get(k);
            if (node == null) {
                if (caches.size() >= CacheCapcity) {
                    caches.remove(last.key);
                    removeLast();
                }
                node = new CacheNode();
                node.key = k;
            }
            node.value = v;
            moveToFirst(node);
            caches.put(k, node);
        }

        public Object get(K k) {
            CacheNode node = caches.get(k);
            if (node == null) {
                return null;
            }
            moveToFirst(node);
            return node.value;
        }

        public Object remove(K k) {
            CacheNode node = caches.get(k);
            if (node != null) {
                if (node.pre != null) {
                    node.pre.next = node.next;
                }
                if (node.next != null) {
                    node.next.pre = node.pre;
                }
                if (node == first) {
                    first = node.next;
                }
                if (node == last) {
                    last = node.pre;
                }
            }
            return caches.remove(k);
        }

        public void clear() {
            first = null;
            last = null;
            caches.clear();
        }

        private void moveToFirst(CacheNode node) {
            if (first == node) {
                return;
            }
            if (node.next != null) {
                node.next.pre = node.pre;
            }
            if (node.pre != null) {
                node.pre.next = node.next;
            }
            if (node == last) {
                last = last.pre;
            }
            if (first == null || last == null) {
                first = last = node;
                return;
            }
            node.next = first;
            first.pre = node;
            first = node;
            first.pre = null;
        }

        private void removeLast() {
            if (last != null) {
                last = last.pre;
                if (last == null) {
                    first = null;
                } else {
                    last.next = null;
                }
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            CacheNode node = first;
            while (node != null) {
                sb.append(String.format("%s:%s ", node.key, node.value));
                node = node.next;
            }
            return sb.toString();
        }

        class CacheNode {
            CacheNode pre;
            CacheNode next;
            Object key;
            Object value;

            public CacheNode() {
            }
        }

    }
}

