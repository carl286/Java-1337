package com.l1337.l432;

//https://leetcode.com/problems/all-oone-data-structure/description/

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AllOne {
    static class Node {
        Node pre;
        Node next;
        int val;
        Set<String> set;
        Node(int val) {
            this.val = val;
            this.set = new HashSet<>();
            pre = null;
            next = null;
        }
    }
    private Map<String, Integer> mapper;
    private Map<Integer, Node> metrics;
    private Node head;
    /** Initialize your data structure here. */
    public AllOne() {
        mapper = new HashMap<>();
        metrics = new HashMap<>();
        head = new Node(0);
        head.pre = head;
        head.next = head;
        metrics.put(0, head);
    }

    //n is in circular linked list, insert newNode after node n
    private void insertAfter(Node n, Node newNode) {
        Node next = n.next;
        newNode.next = next;
        newNode.pre = n;
        n.next = newNode;
        next.pre = newNode;

    }

    //n is a node in circular linked list, remove node n from circular list
    private void remove(Node n) {
        //there are at least two nodes in the list
        Node pre = n.pre;
        Node next = n.next;
        n.pre = null;
        n.next = null;
        pre.next = next;
        next.pre = pre;
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        int oldVal = 0;
        if (mapper.containsKey(key)) {
            oldVal = mapper.get(key);
        }
        mapper.put(key, oldVal + 1);

        Node n = metrics.get(oldVal);
        n.set.remove(key);

     if (n.val + 1 == n.next.val) {
            n.next.set.add(key);
        } else {
            Node newNode = new Node(n.val + 1);
            newNode.set.add(key);
            //metrics should not have a key as n.val + 1, otherwise next.val must be n.val + 1
            metrics.put(n.val + 1, newNode);

            insertAfter(n, newNode);
        }

        if (n.val != 0 && n.set.isEmpty()) {
            remove(n);
            metrics.remove(oldVal);
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (mapper.containsKey(key)) {
            int oldVal = mapper.get(key);
            if (mapper.get(key) == 1) {
                mapper.remove(key);
            }
            else {
                mapper.put(key, oldVal - 1);
            }

            Node n = metrics.get(oldVal);
            n.set.remove(key);
            Node pre = n.pre;

            if (n.set.isEmpty()) {
                remove(n);
                metrics.remove(oldVal);
            }

            if (oldVal > 1) {
                if (pre.val + 1 == oldVal) {
                    pre.set.add(key);
                } else {
                    Node newNode = new Node(oldVal - 1);
                    metrics.put(oldVal - 1, newNode);
                    newNode.set.add(key);

                    insertAfter(pre, newNode);
                }
            }
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        Node n = metrics.get(head.pre.val);
        if (n.set.isEmpty())
            return "";
        return n.set.iterator().next();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        Node n = metrics.get(head.next.val);
        if (n.set.isEmpty())
            return "";
        return n.set.iterator().next();
    }

    //this problem is a good one on pointer manipulication

//    https://leetcode.com/problems/all-oone-data-structure/discuss/91400/All-in-O(1)-with-detailed-explantation
//    https://leetcode.com/problems/all-oone-data-structure/discuss/91398/C++-solution-with-comments

    public static void main(String [] args) {
        AllOne obj = new AllOne();
        System.out.println(obj.getMaxKey());
        System.out.println(obj.getMinKey());
        System.out.println();

        obj.inc("x");
        obj.inc("y");
        obj.inc("z");
        obj.inc("z");
        System.out.println(obj.getMaxKey());
        System.out.println(obj.getMinKey());
        System.out.println();
        obj.inc("x");
        System.out.println(obj.getMaxKey());
        System.out.println(obj.getMinKey());
        System.out.println();
        obj.inc("x");
        System.out.println(obj.getMaxKey());
        System.out.println(obj.getMinKey());
        System.out.println();
        obj.dec("x");
        System.out.println(obj.getMaxKey());
        System.out.println(obj.getMinKey());
        System.out.println();
        obj.dec("x");
        System.out.println(obj.getMaxKey());
        System.out.println(obj.getMinKey());
        System.out.println();
        obj.dec("x");
        System.out.println(obj.getMaxKey());
        System.out.println(obj.getMinKey());

        obj.inc("x");
        obj.inc("x");
        System.out.println(obj.getMaxKey());
        System.out.println(obj.getMinKey());
        System.out.println();

        obj.inc("y");
        System.out.println(obj.getMaxKey());
        System.out.println(obj.getMinKey());
        System.out.println();
    }
}
