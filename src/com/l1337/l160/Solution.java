package com.l1337.l160;


import com.l1337.common.ListNode;

public class Solution {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;

        int countA = 0, countB = 0;
        ListNode lastA = null, lastB = null;

        ListNode cur = headA;
        while (cur != null)
        {
            lastA = cur;
            ++countA;
            cur = cur.next;
        }

        cur = headB;
        while (cur != null)
        {
            lastB = cur;
            ++countB;
            cur = cur.next;
        }

        if (lastA != lastB)
            return null;

        while (countA > countB)
        {
            headA = headA.next;
            --countA;
        }

        while (countB > countA)
        {
            headB = headB.next;
            --countB;
        }

        while (headA != headB)
        {
            headA = headA.next;
            headB = headB.next;
        }

        return headA;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
