package com.l1337.l1033;


import java.util.Arrays;

public class Solution {

//    https://leetcode.com/problems/moving-stones-until-consecutive/
    public int[] numMovesStones(int a, int b, int c) {
        int [] tmp = new int [] {a,b,c};
        Arrays.sort(tmp);
        int [] ret = new int[2];
        ret[1] = tmp[2] - tmp[0] - 2;
        ret[0] = 2;
        if (tmp[1] + 1 == tmp[2] && tmp[0] + 1 == tmp[1])
            ret[0] = 0;
        else if (tmp[1] - tmp[0] <= 2 || tmp[2] - tmp[1] <= 2)
            ret[0] = 1;

        return ret;
    }

//    https://leetcode.com/problems/moving-stones-until-consecutive-ii/discuss/286707/JavaC%2B%2BPython-Sliding-Window
    // poor description,
    public int[] numMovesStonesII(int[] stones) {

        int [] ret = new int[2];

        return ret;
    }
//    https://leetcode.com/problems/moving-stones-until-consecutive-ii/
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
