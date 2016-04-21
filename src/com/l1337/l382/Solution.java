package com.l1337.l382;

//https://leetcode.com/problems/linked-list-random-node/

import com.l1337.common.ListNode;

import java.util.ArrayList;
import java.util.Random;

//https://www.hrwhisper.me/leetcode-linked-list-random-node/

public class Solution {

    private final Random random;
    private final ArrayList<Integer> l;
    /** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        random = new Random();
        l = new ArrayList<>();
        while (head != null) {
            l.add(head.val);
            head = head.next;
        }
    }

    /** Returns a random node's value. */
    public int getRandom() {
        return l.get(random.nextInt(l.size()));
    }

    public static void main(String [] args) {
//        Solution s = new Solution();
//        System.out.println("Hello World");
    }
}
