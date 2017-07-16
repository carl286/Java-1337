package com.l1337.l23;


import com.l1337.common.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> (a.val - b.val));
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int i = 0; i < lists.length; ++i)
            if (lists[i] != null)
                pq.offer(lists[i]);

        while (!pq.isEmpty()) {
            ListNode n = pq.poll();
            cur.next = n;
            cur = n;
            if (n.next != null)
                pq.offer(n.next);
        }
        return dummy.next;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
