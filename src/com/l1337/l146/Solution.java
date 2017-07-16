package com.l1337.l146;


import java.util.HashMap;
import java.util.Map;

public class Solution {

    static class Node {
        int key;
        int value;
        Node pre = null;
        Node next = null;
    }

    static class LRUCache {

        private Node _head;
        private Node _tail;
        private int _capacity;
        private Map<Integer, Node> _map;


        public LRUCache(int capacity) {
            this._head = null;
            this._tail = null;
            this._capacity = capacity;
            this._map = new HashMap<>();
        }

        public int get(int key) {
            Node cache = this._map.get(key);
            if (cache == null)
                return -1;

            RemoveFromList(cache);
            InsertAtTail(cache);
            return cache.value;
        }

        public void put(int key, int value) {
            Node cache = this._map.get(key);
            if (cache != null)
            {
                RemoveFromList(cache);
                InsertAtTail(cache);
                cache.value = value;
                return;
            }

            //assume capacity > 0
            if (this._map.size() >= this._capacity)
            {
                //remove one node for capacity....
                Node head = this._head;
                this._map.remove(head.key);
                RemoveFromList(head);
            }

            //insert a new node
            Node node = new Node();
            node.key = key;
            node.value = value;
            InsertAtTail(node);
            this._map.put(node.key, node);
        }

        private void InsertAtTail(Node node)
        {
            if (this._head == null)
            {
                this._head = node;
                this._tail = node;
            }
            else
            {
                //at least one node, which means head and tail should not empty
                this._tail.next = node;
                node.pre = this._tail;
                this._tail = node;
                this._tail.next = null;
            }
        }

        private void RemoveFromList(Node node)
        {
            Node pre = node.pre;
            Node next = node.next;
            if (pre != null) {
                pre.next = next;
                if (next != null)
                {
                    next.pre = pre;
                }
                else
                {
                    this._tail = pre;
                }
            }
            else
            {
                //_pre is null;
                this._head = next;

                if (next != null)
                {
                    next.pre = null;
                }
                else
                {
                    this._tail = null;
                }
            }
        }
    }

    public static void main(String [] args) {
        Solution s = new Solution();

        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // return -1 (not found)
        System.out.println(lRUCache.get(3));    // return 3
        System.out.println(lRUCache.get(4));    // return 4
    }
}
