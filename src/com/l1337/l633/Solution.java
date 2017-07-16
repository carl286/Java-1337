package com.l1337.l633;


public class Solution {


//    https://leetcode.com/problems/sum-of-square-numbers/discuss/104930/Java-Two-Pointers-Solution
//    https://leetcode.com/problems/sum-of-square-numbers/discuss/104932/HashSet-Java-quick-solution-one-for-loop
//    https://leetcode.com/problems/sum-of-square-numbers/discuss/142484/there-is-a-theorem-named-Fermat's-theorem-on-sums-of-two-squares
    public boolean judgeSquareSum(int c) {
        // c >= 0
        int x = (int)Math.sqrt(c);
        while (x >= 0) {
            int delta = c - x * x;
            int y = (int) Math.sqrt(delta);
            if (y*y == delta)
                return true;
            --x;
        }

        return false;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.judgeSquareSum(4));
    }
}
