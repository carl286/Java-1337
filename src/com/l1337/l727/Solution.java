package com.l1337.l727;


public class Solution {

    /*
    https://anshika-bhargava0202.medium.com/leetcode-727-minimum-window-subsequence-21c40baff689

    Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.
If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.

Input:
S = "abcdebdde", T = "bde"
Output: "bcde"
Explanation:
"bcde" is the answer because it occurs before "bdde" which has the same length.
"deb" is not a smaller window because the elements of T in the window must occur in order.


Note:

All the strings in the input will only contain lowercase letters.
The length of S will be in the range [1, 20000].
The length of T will be in the range [1, 100].
     */
//    https://www.cnblogs.com/grandyang/p/8684817.html
    public String minWindow(String S, String T) {
        //both string are at least 1
        // Write your code here
        int lengthS = S.length();
        int lengthT = T.length();

        if (lengthS < lengthT)
            return null;
        int [][] dp = new int[lengthT][lengthS];

        //init
        int last = 0; // 0 means not possible...
        for(int i = 0; i < S.length(); ++i)
        {
            if (S.charAt(i) == T.charAt(0))
            {
                last = dp[0][i] = 1;
            }
            else if (last > 0)
            {
                dp[0][i] = ++last;
            }
        }

        for(int j = 1; j < lengthT; ++j)
        {
            last = 0;
            boolean progress = false;
            for(int i = j; i < S.length(); ++i)
            {
                //dp[j][i]
                if (S.charAt(i) == T.charAt(j) && dp[j-1][i-1] > 0)
                {
                    dp[j][i] = 1 + dp[j-1][i-1];
                    last = dp[j][i];
                    progress = true;
                } else if (last > 0)
                {
                    dp[j][i] = ++last;
                }
            }
            if (!progress)
                return null;
        }

        int endIndex = 0, minLength = lengthS + 1;
        for(int i = lengthT - 1; i < lengthS; ++i)
        {
            if (dp[lengthT-1][i] > 0 && dp[lengthT-1][i] < minLength)
            {
                minLength = dp[lengthT-1][i];
                endIndex = i;
            }
        }
        if (minLength > lengthS)
            return null;
        return S.substring(endIndex - minLength + 1, endIndex + 1);
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.minWindow("abababababababccc", "bbc"));
    }
}
