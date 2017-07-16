package com.l1337.l92;


import com.l1337.common.ListNode;

public class Solution {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n)
            return head;

        ListNode dummy = new ListNode(0);
        ListNode pre = dummy, cur = head;
        dummy.next = head;

        int i = 1;
        while ( i < m)
        {
            pre = cur;
            cur = cur.next;
            ++i;
        }

        ++i;
        ListNode subTail = cur;
        cur = cur.next;
        while (i <= n)
        {
            ListNode next = cur.next;
            cur.next = pre.next;
            subTail.next = next;
            pre.next = cur;
            cur = next;
            ++i;
        }

        return dummy.next;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        int m = 2, n = 4;
        ListNode.print(s.reverseBetween(l1, m, n));
        System.out.println("Hello World");
    }
}
