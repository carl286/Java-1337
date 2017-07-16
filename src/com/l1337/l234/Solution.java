package com.l1337.l234;


import com.l1337.common.ListNode;

public class Solution {

    public ListNode reverse(ListNode head)
    {
        ListNode dummy = new ListNode(0);
        while (head != null)
        {
            ListNode next = head.next;
            head.next = dummy.next;
            dummy.next = head;
            head = next;
        }

        return dummy.next;
    }
    public int getLength(ListNode root)
    {
        int ret = 0;
        while (root != null)
        {
            root = root.next;
            ++ret;
        }
        return ret;
    }
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;

        int length = getLength(head);
        int half = length / 2;
        ListNode pre = null, cur = head;
        while (half-- > 0)
        {
            pre = cur;
            cur = cur.next;
        }

        if ((length & 0x01) != 0)
            cur = cur.next;
        pre.next = null;
        head = reverse(head);
        while (head != null)
        {
            if (head.val != cur.val)
                return false;
            head = head.next;
            cur = cur.next;
        }


        return true;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
