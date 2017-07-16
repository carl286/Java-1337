package com.l1337.l605;


public class Solution {

    private int f(int n) {
        if (n <= 0)
            return 0;
//        return (n + 1) / 2;
        return (n + 1) >> 1;
    }

//    https://leetcode.com/problems/can-place-flowers/discuss/172328/a-clean-python-solution-with-explanation
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n > f(flowerbed.length)) //this is the upup limit
            return false;

        int i = 0, j = 0;
        while (i < flowerbed.length && n > 0) {
            while (i < flowerbed.length && flowerbed[i] == 1) {
                ++i;
            }
            j = i;
            while (j < flowerbed.length && flowerbed[j] == 0) {
                ++j;
            }
            int l = j - i;
            if (i != 0)
                --l;
            if (j != flowerbed.length)
                --l;
            n -= f(l);

            i = j;
        }

        return n <= 0;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.canPlaceFlowers(new int[]{1}, 1));
    }
}
