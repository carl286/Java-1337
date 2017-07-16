package com.l1337.l138;


public class Solution {

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null)
            return  null;
        Node cur = head;

        while (cur != null)
        {
            Node mapped = new Node(cur.val);
            Node next = cur.next;
            cur.next = mapped;
            mapped.next = next;
            cur = next;
        }

        Node ret = head.next;
        cur = head;
        while (cur != null)
        {
            if (cur.random != null)
            {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        cur = head;
        while (cur != null)
        {
            Node mapped = cur.next;
            Node next = mapped.next;
            cur.next = next;
            mapped.next = next != null ? next.next : null;
            cur = cur.next;
        }

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
