package com.l1337.uglyNumber;


import java.util.Arrays;

public class Solution {

//    https://leetcode.com/problems/ugly-number/
//    263. Ugly Number
    boolean isUgly(int num) {
        if (num <= 0)
            return false;

        //To speed up, divide large number first???
        //I didn't see much diff
        while (num > 1 && (num % 2) == 0)
            num /= 2;
        while (num > 1 && (num % 3) == 0)
            num /= 3;
        while (num > 1 && (num % 5) == 0)
            num /= 5;
        return num == 1;
    }

//    https://leetcode.com/problems/super-ugly-number/
//    313. Super Ugly Number

//    https://leetcode.com/submissions/detail/58186520/
    //I don't even think below is a correct ans...
    public int nthSuperUglyNumber(int n, int[] primes) {
        //assume n > 0;
        int ret [] = new int [n];
        int last [] = new int[primes.length];
        ret[0] = 1;
        for (int i = 1; i < n; ++i) {
            int curMin = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; ++j)
                curMin = Math.min(curMin, ret[last[j]] * primes[j]);

            for (int j = 0; j < primes.length; ++j)
                //Why this condition????
                 //Yap, this is correct due to prime's proerty.....
                if (curMin % primes[j] == 0)
                    ++last[j];
            ret[i] = curMin;
        }

        return ret[n-1];
    }
    public static void main(String [] args) {
        Solution s = new Solution();
//        for (int i = 0; i <= 20; ++i)
//            System.out.println(i + "\t" + s.isUgly(i));

        int[] primes = {2, 7, 13, 19};
        for (int i = 1; i <= 20; ++i)
            System.out.println(i + "\t" + s.nthSuperUglyNumber(i, primes));
        System.out.println(11 + "\t" + s.nthSuperUglyNumber(11, primes));
    }
}
