package com.l1337.l445;


import java.util.List;

import com.l1337.common.ListNode;

public class Solution {

    private int length(ListNode n) {
        int i = 0;
        while (n != null) {
            n = n.next;
            ++i;
        }
        return i;
    }

    private ListNode reverse(ListNode n) {
        ListNode pre = null, cur = n, next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;

            pre =cur;
            cur = next;
        }

        return pre;
    }
//    https://leetcode.com/problems/add-two-numbers-ii/description/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return null;
    }

//    https://leetcode.com/submissions/detail/143528096/
//    https://leetcode.com/problems/add-two-numbers-ii/discuss/92623/Easy-O(n)-Java-Excel-using-Stack
//    https://leetcode.com/problems/add-two-numbers-ii/discuss/92643/Java-O(n)-recursive-solution-by-counting-the-difference-of-length
//    https://leetcode.com/problems/add-two-numbers-ii/discuss/92643/Java-O(n)-recursive-solution-by-counting-the-difference-of-length
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
