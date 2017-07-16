package com.l1337.l397;


public class Solution {

    public int integerReplacement(int n) {
        //Memory Limit Exceeded

//        int base = 0;
//        while ((n & 0x01) == 0) {
//            n >>= 1;
//            ++base;
//        }
//        int T [] = new int [n + 1];
//        T[1] = 0;
//        for (int i = 2; i <= n; ++i) {
//            if ((i & 0x01) == 0) { //even
//                T[i] = 1 + T[i/2];
//            } else {
//                T[i] = 2 + Math.min(T[(i+1)/2], T[(i-1)/2]);
//            }
//        }
//        return T[n] + base;

//        When n is even, the operation is fixed. The procedure is unknown when it is odd. When n is odd it can be written into the form n = 2k+1 (k is a non-negative integer.). That is, n+1 = 2k+2 and n-1 = 2k. Then, (n+1)/2 = k+1 and (n-1)/2 = k. So one of (n+1)/2 and (n-1)/2 is even, the other is odd. And the "best" case of this problem is to divide as much as possible. Because of that, always pick n+1 or n-1 based on if it can be divided by 4. The only special case of that is when n=3 you would like to pick n-1 rather than n+1.
        return 0;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.integerReplacement(111111111));
    }
}
