package com.l1337.l24;


import com.l1337.common.ListNode;

import java.util.PriorityQueue;

public class Solution {

    public ListNode swapPairs(ListNode head) {
        ListNode ret = new ListNode(0), oddList = new ListNode(0), evenList = new ListNode(0);
        ListNode cur = ret, curOdd = oddList, curEven = evenList;

        boolean flag = true;
        while (head != null) {
            if (flag) {
                //odd first
                curOdd.next = head;
                curOdd = curOdd.next;
            } else {
                curEven.next = head;
                curEven = curEven.next;
            }
            flag = !flag;
            head = head.next;
        }
        curEven.next = null;
        curOdd.next = null;

        //merge
        flag = true;
        oddList = oddList.next;
        evenList = evenList.next;
        while (oddList != null || evenList != null) {
            //even first
            if (evenList != null && oddList != null) {
                if (flag) {
                    cur.next = evenList;
                    cur = cur.next;
                    evenList = evenList.next;
                } else {
                    cur.next = oddList;
                    cur = cur.next;
                    oddList = oddList.next;
                }
                flag = !flag;
            } else {
                cur.next = oddList;
                cur = cur.next;
                oddList = oddList.next;
            }
        }
        cur.next = null;

        return ret.next;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        ListNode t1 = new ListNode(1);
        ListNode t2 = new ListNode(2);
        ListNode t3 = new ListNode(3);
        ListNode t4 = new ListNode(4);
        t1.next = t2;
        t2.next = t3;
        t3.next = t4;
        s.swapPairs(t1);
        System.out.println("Hello World");
    }
}
