package com.l1337.l762;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    private static Set<Integer> primeSets = new HashSet<Integer>(Arrays.asList(new Integer [] {2,3,5,7,11,13,17,19,23,29}));

    private int countBits(int i) {
        int count = 0;
        while (i != 0) {
            i &= (i - 1);
            ++count;
        }

        return count;
    }
    public int countPrimeSetBits(int L, int R) {

        int ret = 0;

        for (int i = L; i <= R; ++i) {
            int bits = countBits(i);
            if (primeSets.contains(bits))
                ++ret;
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.countPrimeSetBits(10, 15));
    }
}
