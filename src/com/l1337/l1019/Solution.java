package com.l1337.l1019;


import com.l1337.common.ListNode;
import javafx.util.Pair;

import java.util.*;

public class Solution {

    private ListNode reverseList(ListNode root)
    {
        ListNode pre = null, cur = root, next = null;
        while (cur != null)
        {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

//    https://leetcode.com/problems/next-greater-node-in-linked-list/discuss/265508/JavaC%2B%2BPython-Next-Greater-Element
    public int[] nextLargerNodes(ListNode head) {
        ListNode cur = head;
        int count = 0;
        while (cur != null)
        {
            cur = cur.next;
            ++count;
        }

        PriorityQueue<int []> pq = new PriorityQueue<>((a, b) -> (a[0]-b[0]));

        int [] ret = new int [count];
        cur = head;
        count = 0;
        while (cur != null)
        {
            while (!pq.isEmpty() && pq.peek()[0] < cur.val)
            {
                int [] node = pq.poll();
                ret[node[1]] = cur.val;
            }
            pq.add(new int [] {cur.val, count});
            ++count;
            cur = cur.next;
        }
        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        ListNode n0 = new ListNode(2);
        ListNode n1 = new ListNode(7);
        ListNode n2 = new ListNode(4);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(5);
        n0.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        for (int i:
                s.nextLargerNodes(n0)) {
            System.out.println(i);
        }

    }
}
