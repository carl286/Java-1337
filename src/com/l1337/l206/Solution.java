package com.l1337.l206;


import com.l1337.common.ListNode;

public class Solution {

    public ListNode reverseList(ListNode head) {
        ListNode last = null;
        while (head != null)
        {
            ListNode next = head.next;
            head.next = last;
            last = head;
            head = next;
        }

        return last;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
