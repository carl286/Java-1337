package com.l1337.common;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }

    public static void print(ListNode root)
    {
        while (root != null)
        {
            System.out.print(root.val + "\t");
            root = root.next;
        }
    }
}
