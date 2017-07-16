package com.l1337.l1062;


public class Solution {

    /*
    Longest Repeating Substring
    https://leetcode.com/problems/longest-repeating-substring/
    Given a string S, find out the length of the longest repeating substring(s). Return 0 if no repeating substring exists.

Example 1:

Input: “abcd”
Output: 0
Explanation: There is no repeating substring.
Example 2:

Input: “abbaba”
Output: 2
Explanation: The longest repeating substrings are “ab” and “ba”, each of which occurs twice.
Example 3:

Input: “aabcaabdaab”
Output: 3
Explanation: The longest repeating substring is “aab”, which occurs 3 times.
Example 4:

Input: “aaaaa”
Output: 4
Explanation: The longest repeating substring is “aaaa”, which occurs twice.


Constraints:

The string S consists of only lowercase English letters from ‘a’ - ‘z’.
1 <= S.length <= 1500
     */

    /*
    这题我们采用动态规划的方法。
我们先定义dp[i][j]为分别以第i个字符和第j个字符结尾的substring有相同共同后缀的最大长度。因此，我们也要求i>j。
我们注意到，当S[i] != S[j], 那么dp[i][j] = 0， 否则dp[i][j] = dp[i-1][j-1] + 1。这就是我们的状态转移方程。

dp[i][j] = dp[i-1][j-1] + 1 ----------- S[i] == S[j]
dp[i][j] = 0 ----------- S[i] != S[j]

我们更新dp[i][j]的最大值，就可以得到最后的答案。


int longestRepeatingSubstring(string S) {
        int ans = INT_MIN;
        vector<vector<int>> dp(S.size() + 1, vector<int>(S.size() + 1, 0));
        for (auto i = 1; i <= S.size(); i++) {
            for (auto j = 1; j < i; j++) {
                if (S[i-1] == S[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                ans = max(ans, dp[i][j]);
            }
        }
        return ans;
    }
     */
    public int longestRepeatingSubstring(String S) {
        int res = 0;
        int n = S.length();
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            // Need start from i - 1 to use values from last iteration
            for (int j = i - 1; j >= 1; j--) {
                if (S.charAt(i - 1) == S.charAt(j - 1)) {
                    dp[j] = dp[j - 1] + 1;
                } else {
                    dp[j] = 0;
                }

                res = Math.max(res, dp[j]);
            }
        }

        return res;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
