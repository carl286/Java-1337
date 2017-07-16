package com.l1337.l600;


public class Solution {

//    https://www.geeksforgeeks.org/count-number-binary-strings-without-consecutive-1s/
//    https://leetcode.com/problems/non-negative-integers-without-consecutive-ones/discuss/103749/Java-Solution-DP
//    https://leetcode.com/problems/non-negative-integers-without-consecutive-ones/discuss/103751/Java-O(1)-time-O(1)-space-DP-Excel
//    https://leetcode.com/problems/non-negative-integers-without-consecutive-ones/discuss/103754/C++-Non-DP-O(32)-Fibonacci-solution
//    https://leetcode.com/problems/non-negative-integers-without-consecutive-ones/discuss/103766/C++-4-lines-DPFibonacci-6-ms
    private int dfs(int currentValue, int num, int currentMask, boolean isLastOne) {
        if (currentValue > num)
            return 0;

        if (currentMask == 0)
            return 1;

        int ret = 0;
        //try 1.
        if (!isLastOne)
            ret += dfs(currentValue | currentMask, num, currentMask >> 1, true);
        ret += dfs(currentValue , num, currentMask >> 1, false);

        return ret;
    }


    public int findIntegers(int num) {
//
//        // 1 <= n <= 109
//        int highOne = Integer.highestOneBit(num);
//
//        return dfs(0, num, highOne, false);
        StringBuilder sb = new StringBuilder(Integer.toBinaryString(num)).reverse();
        int n = sb.length();

        int a[] = new int[n];
        int b[] = new int[n];
        a[0] = b[0] = 1;
        for (int i = 1; i < n; i++) {
            a[i] = a[i - 1] + b[i - 1];
            b[i] = a[i - 1];
        }

        int result = a[n - 1] + b[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (sb.charAt(i) == '1' && sb.charAt(i + 1) == '1') break;
            if (sb.charAt(i) == '0' && sb.charAt(i + 1) == '0') result -= b[i];
        }

        return result;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
//        for (int i = 1; i <= 10; ++i)
            System.out.println(s.findIntegers(4));
    }
}
