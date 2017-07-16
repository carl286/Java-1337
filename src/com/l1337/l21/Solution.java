package com.l1337.l21;


import com.l1337.common.ListNode;

import java.util.Stack;

public class Solution {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                cur.next = l2;
                cur = l2;
                l2 = l2.next;
            } else if (l2 == null) {
                cur.next = l1;
                cur = l1;
                l1 = l1.next;
            } else if (l1.val < l2.val) {
                cur.next = l1;
                cur = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                cur = l2;
                l2 = l2.next;
            }
        }


        return dummy.next;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}