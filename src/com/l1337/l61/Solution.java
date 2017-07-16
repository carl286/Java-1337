package com.l1337.l61;


import com.l1337.common.ListNode;

public class Solution {
    private int getListLength(ListNode head) {
        int i = 0;
        while (head != null) {
            ++i;
            head = head.next;
        }

        return i;
    }
    public ListNode rotateRight(ListNode head, int k) {
        int length = getListLength(head);
        if (length <= 1)
            return head;

        k %= length;
        if (k == 0)
            return head;

        k = length - k;

        ListNode tmp = head;
        while (k > 1) {
            head = head.next;
            --k;
        }

        ListNode ret = head.next;
        head.next = null;

        ListNode tmp2 = ret;
        while (tmp2.next != null) {
            tmp2 = tmp2.next;
        }

        tmp2.next = tmp;

        return ret;
    }

    public static void main(String [] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        n1.next = n2;
        ListNode n3 = new ListNode(3);
        n2.next = n3;
        ListNode n4 = new ListNode(4);
        n3.next = n4;
        ListNode n5 = new ListNode(5);
        n4.next = n5;

        Solution s = new Solution();
        ListNode ret = s.rotateRight(n1, 2);
        while (ret != null) {
            System.out.print(ret.val + "\t");
            ret = ret.next;
        }
        System.out.println();
    }
}
