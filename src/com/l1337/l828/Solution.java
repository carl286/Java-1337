package com.l1337.l828;


import java.io.ByteArrayInputStream;
import java.util.Arrays;

public class Solution {

    public int uniqueLetterString(String s) {
        int baseModule = 1000000000 + 7;
        int n = s.length();
//        //Memory Limit Exceeded
//        int [][] uniqueSet = new int [n][n];
//        int [][] duplicate = new int [n][n];
//        int [][] count = new int [n][n];
//
//        int ret = 0;
//        for (int i = n - 1; i >= 0; --i)
//        {
//            count[i][i] = 1;
//            ret = (ret + count[i][i]) % baseModule;
//            uniqueSet[i][i] = (1 << (s.charAt(i) - 'A'));
//
//            for (int j = i + 1; j < n; ++j)
//            {
//                int index = s.charAt(j) - 'A';
//                //check i to j
//                int targetMask = 1 << index;
//                uniqueSet[i][j] = uniqueSet[i][j-1];
//                duplicate[i][j] = duplicate[i][j-1];
//                if ((uniqueSet[i][j-1] & (targetMask)) == 0)
//                {
//                    if ((duplicate[i][j-1] & (targetMask)) == 0)
//                    {
//                        //never seen the character before
//                        count[i][j] = 1 + count[i][j-1];
//                        uniqueSet[i][j] = uniqueSet[i][j-1] | targetMask;
//                    }
//                    else
//                    {
//                        //seen it as duplicate already
//                        count[i][j] = count[i][j-1];
//                    }
//                }
//                else
//                {
//
//                    count[i][j] = count[i][j-1] - 1;
//                    uniqueSet[i][j] = uniqueSet[i][j-1] & ~targetMask;
//                    duplicate[i][j] = duplicate[i][j-1] | targetMask;
//                }
//                ret = (ret + count[i][j]) % baseModule;
//            }
//        }

//        for(int i = 0; i < n; ++i)
//        {
//            for(int j = i; j < n; ++j)
//            {
//                System.out.print("(" + i + "," + j + ")" + s.substring(i, j + 1) + ":" + count[i][j] + "\t");
//            }
//            System.out.println("line: " + i);
//        }

        //Time Limit Exceeded below....
        // O(n2) is not good enough, look for O(n)
//        int ret = 0;
//        for (int i = n - 1; i >= 0; --i)
//        {
//            int count = 1;
//            ret = (ret + count) % baseModule;
//            int unique = (1 << (s.charAt(i) - 'A'));
//            int duplicate = 0;
//
//            for (int j = i + 1; j < n; ++j)
//            {
//                int index = s.charAt(j) - 'A';
//                //check i to j
//                int targetMask = 1 << index;
//
//                if ((unique & (targetMask)) == 0)
//                {
//                    if ((duplicate & (targetMask)) == 0)
//                    {
//                        //never seen the character before
//                        ++count;
//                        unique |= targetMask;
//                    }
//                }
//                else
//                {
//
//                    --count;
//                    unique &= ~targetMask;
//                    duplicate |= targetMask;
//                }
//                ret = (ret + count) % baseModule;
//            }
//        }

        int[][] index = new int[26][2];
        for (int i = 0; i < 26; ++i) Arrays.fill(index[i], -1);
        int res = 0, N = s.length(), mod = (int)Math.pow(10, 9) + 7;
        for (int i = 0; i < N; ++i) {
            int c = s.charAt(i) - 'A';
            res = (res + (i - index[c][1]) * (index[c][1] - index[c][0]) % mod) % mod;
            index[c] = new int[] {index[c][1], i};
        }
        for (int c = 0; c < 26; ++c)
            res = (res + (N - index[c][1]) * (index[c][1] - index[c][0]) % mod) % mod;
        return res;
    }

//    https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/discuss/128952/C%2B%2BJavaPython-One-pass-O(N)
//    https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/discuss/129021/O(N)-Java-Solution-DP-Clear-and-easy-to-Understand
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.uniqueLetterString("LEETCODE"));
        // System.out.println(s.uniqueLetterString("LEETE"));
    }
}
