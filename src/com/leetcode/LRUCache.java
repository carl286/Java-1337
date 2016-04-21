package com.leetcode;

//146. LRU Cache
//https://leetcode.com/problems/lru-cache/

import java.util.HashMap;

class Node {
    int key;
    int val;
    Node pre;
    Node next;
}

//http://www.programcreek.com/2013/03/leetcode-lru-cache-java/
//You can create the new node on the fly..
public class LRUCache {

    final private HashMap<Integer, Node> map;
    final private Node [] freePool;
    private Node head;
    private Node tail;
    private int capacity;


    private void addToTail(Node node) {
        //node not in the used list
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.pre = tail;
            tail = node;
        }
    }

    private void moveToTail(Node node) {
        //node already in the used list
        if (node == tail)
            return;
        if (node == head) {
            head = head.next;
        } else {
            node.next.pre = node.pre;
            node.pre.next = node.next;
        }
        tail.next = node;
        node.pre = tail;
        tail = node;
    }

    public LRUCache(int capacity) {
        map = new HashMap<>();
        freePool = new Node[capacity];
        //You need instantiate all OBJECTS BY YOURSELF..
        for (int i = 0; i < capacity; i++)
            freePool[i] = new Node();
        this.capacity = capacity;
        head = null;
        tail = null;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null)
            return -1;
        else
            moveToTail(node);

        return node.val;
    }

    public void set(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.val = value;
            moveToTail(node);
        } else {
            if (capacity == 0) {
                node = head;
                moveToTail(node);
                map.remove(node.key);
            } else {
                node = freePool[--capacity];
                addToTail(node);
            }
            node.key = key;
            node.val = value;
            //update KV
            map.put(node.key, node);
        }
    }

    public static void main(String [] args) {
        LRUCache lru = new LRUCache(1);
        lru.set(2,1);
        System.out.println(lru.get(1));
    }
}
