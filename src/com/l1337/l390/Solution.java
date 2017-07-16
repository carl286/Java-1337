package com.l1337.l390;


import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    public int lastRemaining(int n) {
        //Below versin exceed time limit
//        if (n <= 1)
//            return n;
//        Deque<Integer> primayQueue = new ArrayDeque<>();
//        Deque<Integer> backupQueue = new ArrayDeque<>();
//        for (int i = 1; i <= n; ++i)
//            primayQueue.addLast(i);
//        boolean isReverse = false;
//
//        while (primayQueue.size() > 1) {
//            boolean toAdd =false;
//            while (!primayQueue.isEmpty()) {
//                Integer tmp;
//                if (isReverse) {
//                    tmp = primayQueue.removeLast();
//                } else {
//                    tmp = primayQueue.removeFirst();
//                }
//                if (toAdd) {
//                    if (isReverse)
//                        backupQueue.addFirst(tmp);
//                    else
//                        backupQueue.addLast(tmp);
//                }
//                toAdd = !toAdd;
//            }
//            Deque<Integer> tmp = backupQueue;
//            backupQueue = primayQueue;
//            primayQueue = tmp;
//            isReverse = !isReverse;
//        }
//        return primayQueue.getFirst();
        return n == 1 ? 1 : 2 * (1 + n / 2 - lastRemaining(n / 2));
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.lastRemaining(10000000));
    }
}
