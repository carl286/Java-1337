package com.l1337.l19;


import com.l1337.common.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public void removeNthFromEndRecursive(ListNode pre, ListNode cur, int [] array) {
        if (cur.next != null){
            removeNthFromEndRecursive(cur, cur.next, array);
        }

        if (array[0] == 1) {
            if (pre != null) {
                pre.next = cur.next;
                array[0] = 0;
            } else {
                array[0] = -1;
            }
        } else if (array[0] > 1) {
            --array[0];
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int [] array = {n};
        removeNthFromEndRecursive(null, head, array);
        if (array[0] < 0)
            return head.next;
        else
            return head;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
