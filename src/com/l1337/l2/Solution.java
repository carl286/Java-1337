package com.l1337.l2;


import com.l1337.common.ListNode;

public class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode retHead = new ListNode(0);
        ListNode cur = retHead;
        int carryOn = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carryOn;
            if (sum >= 10)
            {
                sum -= 10;
                carryOn = 1;
            }
            else
            {
                carryOn = 0;
            }
            ListNode newNode = new ListNode(sum);
            cur.next = newNode;
            cur = newNode;
            l1 = l1.next;
            l2 = l2.next;
        }

        ListNode remaining = l1 != null ? l1 : l2;
        while (remaining != null)
        {
            int sum = remaining.val + carryOn;
            if (sum >= 10)
            {
                sum -= 10;
                carryOn = 1;
            }
            else
            {
                carryOn = 0;
            }
            ListNode newNode = new ListNode(sum);
            cur.next = newNode;
            cur = newNode;
            remaining = remaining.next;
        }
        if (carryOn > 0){
            ListNode newNode = new ListNode(carryOn);
            cur.next = newNode;
            cur = newNode;
        }
        return retHead.next;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
