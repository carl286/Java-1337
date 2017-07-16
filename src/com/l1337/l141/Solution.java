package com.l1337.l141;


import com.l1337.common.ListNode;

public class Solution {

    public boolean hasCycle(ListNode head) {
        ListNode fast = head,slow = head;
        while (fast != null && (fast = fast.next) != null && (fast = fast.next) != null && (slow = slow.next) != null && fast != slow)
        {
            ;
        }

        return fast != null;
    }

    public ListNode detectCycle(ListNode head) {

        return null;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
