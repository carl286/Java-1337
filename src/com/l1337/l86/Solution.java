package com.l1337.l86;


import com.l1337.common.ListNode;

import java.util.Stack;

public class Solution {

    // https://www.cnblogs.com/grandyang/p/4321292.html
    public ListNode partition(ListNode head, int x) {
        ListNode left = new ListNode(0), right = new ListNode(0), ret = new ListNode(0);
        ListNode leftHead = left, leftTail = left, rightHead = right, rightTail = right;

        while(head != null) {
            if (head.val < x)
            {
                leftTail.next = head;
                leftTail = leftTail.next;
            }
            else
            {
                rightTail.next = head;
                rightTail = rightTail.next;
            }
            head = head.next;
        }
        leftTail.next = null;
        rightTail.next = null;

        //merge
        ListNode cur = ret;
        leftHead = leftHead.next;
        while (leftHead != null)
        {
            cur.next = leftHead;
            cur = leftHead;
            leftHead = leftHead.next;
        }

        rightHead = rightHead.next;
        while (rightHead != null)
        {
            cur.next = rightHead;
            cur = rightHead;
            rightHead = rightHead.next;
        }
        cur.next = null;
        return ret.next;
    }
    
    public static void main(String [] args) {
    }
}
