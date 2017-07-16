package com.l1337.l935;


import java.util.Arrays;

public class Solution {

    public int knightDialer(int n) {
        int base = 1000000000 + 7;
        long tmp [][] = new long [2][10];
        Arrays.fill(tmp[0], 1);
        while (--n > 0)
        {
            tmp[1][0] = (tmp[0][4] + tmp[0][6]) % base;
            tmp[1][1] = (tmp[0][6] + tmp[0][8]) % base;
            tmp[1][2] = (tmp[0][7] + tmp[0][9]) % base;
            tmp[1][3] = (tmp[0][4] + tmp[0][8]) % base;
            tmp[1][4] = ((tmp[0][3] + tmp[0][9]) % base + tmp[0][0]) % base;
            tmp[1][5] = 0;
            tmp[1][6] = ((tmp[0][1] + tmp[0][7]) % base + tmp[0][0]) % base;
            tmp[1][7] = (tmp[0][2] + tmp[0][6]) % base;
            tmp[1][8] = (tmp[0][1] + tmp[0][3]) % base;
            tmp[1][9] = (tmp[0][2] + tmp[0][4]) % base;

            System.arraycopy(tmp[1], 0, tmp[0], 0, tmp[0].length);
        }
        long ret = 0;
        for (int i = 0; i < tmp[0].length; ++i)
            ret = (ret + tmp[0][i]) % base;

        return (int) ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.knightDialer(3131));
    }
}
