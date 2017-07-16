package com.l1337.l83;

import com.l1337.common.ListNode;

public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(head.val - 1);
        ListNode cur = dummy;
        while (head != null)
        {
            if (head.val != cur.val)
            {
                cur.next = head;
                cur = head;
            }

                head = head.next;
        }

        cur.next = null;
        return dummy.next;
    }

    public ListNode deleteDuplicatesII(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (head != null)
        {
            ListNode runner = head;
            while (runner.next != null && runner.next.val == runner.val)
                runner = runner.next;
            if (runner == head)
            {
                cur.next = head;
                cur = head;
            }
            head = runner.next;
        }

        cur.next = null;
        return dummy.next;
    }

    public static void main(String [] args) {
    }
}
