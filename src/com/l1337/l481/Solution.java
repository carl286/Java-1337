package com.l1337.l481;


import java.util.LinkedList;

public class Solution {

//    https://leetcode.com/problems/magical-string/discuss/96413/Simple-Java-solution-using-one-array-and-two-pointers
    public int magicalString(int n) {
        if (n <= 0)
            return 0;
//        if (n <= 3)
//            return 1;

//        1221121221221121122
        int count = 0;
        LinkedList<Integer> l = new LinkedList<>();
        l.add(2);
        count = 1;
        n -= 3;
        int preAdd = 2;
        while (n > 0) {
            int newToAdd = l.pollFirst();
            preAdd = 3 - preAdd;//flip between 1 and 2
            for (int i = 0; i < newToAdd && n > 0; ++i) {
                l.add(preAdd);
                if (preAdd == 1)
                    ++count;
                --n;
            }
        }

        return count;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        for (int i = 0; i < 10; ++i)
            System.out.println(s.magicalString(i));
    }
}
