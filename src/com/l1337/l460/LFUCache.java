package com.l1337.l460;

//https://leetcode.com/problems/lfu-cache/description/

import java.util.HashMap;
import java.util.Map;

public class LFUCache {
    static class Node {
        int key;
        int val;
        int freq;
        Node pre;
        Node next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.freq = 0;
            this.pre = null;
            this.next = null;
        }
    }

    private int capacity;
    private int size;
    private Map<Integer, Node> map;//key -> node
    private Map<Integer, Node> feqs;//frequency -> tail node of that frequency
    private Node head;

    private boolean isFull() {
        return this.size == this.capacity;
    }

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        map = new HashMap<>();
        feqs = new HashMap<>();
        head = new Node(0,0);//this is a dummy head
        head.freq = 0;
        feqs.put(head.freq, head);
    }

    private Node removeLR() {
        //remove a node from linked list
        Node pre = head;
        Node n = head.next;
        if (n != null) {
            //there is at least one node
            Node next = n.next;
            //move n out of the list
            pre.next = next;
            if (next != null) {
                next.pre = pre;
            }

            //update feq list
            if (next == null || next.freq != n.freq) {
                feqs.remove(n.freq);
            }
            map.remove(n.key);
        }
        return n;
    }

    //n is a node in the list
    //move n to the tail of n.freq+1 then update n.freq to n.freq + 1
    private void movetoNextFeqTail(Node n) {
        Node tail = feqs.get(n.freq);
        Node oldPre = tail.pre;//should never be null
        Node next = tail.next;
        if (next != null) {
            if (next.freq != n.freq + 1) {
                if (tail != n) {
                    //move n out of list first
                    Node tpre = n.pre;
                    Node tnext = n.next;
                    tpre.next = tnext;
                    tnext.pre = tpre;

                    //move between tail and next
                    tail.next = n;
                    n.pre = tail;
                    n.next = next;
                    next.pre = n;
                }
            } else {
                //move n out of list first
                Node tpre = n.pre;
                Node tnext = n.next;
                tpre.next = tnext;
                tnext.pre = tpre;
                Node newtail = feqs.get(n.freq + 1);
                n.pre = newtail;
                n.next = newtail.next;
                if (newtail.next != null) {
                    newtail.next.pre = n;
                }
                newtail.next = n;
            }
        } else {
            //tail is the last node in the list. Simply move after tail is good enough
            if (tail != n) {
                //move node n after tail
                Node tpre = n.pre;
                Node tnext = n.next;
                tpre.next = tnext;
                tnext.pre = tpre;
                tail.next = n;
                n.pre = tail;
                n.next = null;
            }
        }

        if (n == tail) {
            //if n and tail is the same node and you moved tail, then the pointer is changed.
            if (tail.freq == oldPre.freq) {
                feqs.put(oldPre.freq, oldPre);
            } else {
                feqs.remove(tail.freq);
            }
        }
        //update feq
        ++n.freq;
        feqs.put(n.freq, n);
    }

    //n is NOT a node in the list
    private void addAfterHead(Node n) {
        n.pre = head;
        n.next = head.next;
        head.next = n;
        feqs.put(n.freq, n);
    }

    public int get(int key) {
        Node n = map.get(key);
        if (n != null) {
            movetoNextFeqTail(n);
            return n.val;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        Node n = map.get(key);
        if (n ==  null) {
            if (isFull()) {
                n = removeLR();
                if (n == null)
                    return; //empty cache
                else {
                    n.key = key;
                    n.val = value;
                    n.freq = 0;
                    n.pre = n.next = null;
                }
            } else {
                n = new Node(key, value);
                ++this.size;
            }

            map.put(key, n);
            addAfterHead(n);
            movetoNextFeqTail(n);
        } else {
            n.val = value;
            movetoNextFeqTail(n);
        }
    }

    public static void main(String [] args) {
        LFUCache cache = new LFUCache(10);
        cache.put(10,13);
        cache.put(3,17);
        cache.put(6,11);
        cache.put(10,5);
        cache.put(9,10);
        System.out.println(cache.get(13));
        cache.put(2,19);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        cache.put(5,25);
        System.out.println(cache.get(8));
        cache.put(9,22);
        cache.put(5,5);
        cache.put(1,30);
        System.out.println(cache.get(11));
        cache.put(9,12);
        System.out.println(cache.get(7));
        System.out.println(cache.get(5));
        System.out.println(cache.get(8));
        System.out.println(cache.get(9));
        cache.put(4,30);
        cache.put(9,3);
        System.out.println(cache.get(9));
        System.out.println(cache.get(10));
        System.out.println(cache.get(10));
        cache.put(6,14);

    }

}
