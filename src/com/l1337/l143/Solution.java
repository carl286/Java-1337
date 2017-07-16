package com.l1337.l143;


import com.l1337.common.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public void reorderList(ListNode head) {
        if (head == null)
            return;
        List<ListNode> list = new ArrayList<>();
        while (head != null)
        {
            list.add(head);
            head = head.next;
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int i = 0, j = list.size() - 1;
        boolean left = true;
        while (i < j)
        {
            if (left)
            {
                cur.next = list.get(i++);
                left = false;
            }
            else
            {
                cur.next = list.get(j--);
                left = true;
            }
            cur = cur.next;
        }

        cur.next = list.get(i);
        cur.next.next = null;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
