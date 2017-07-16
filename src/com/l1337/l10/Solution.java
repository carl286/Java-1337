package com.l1337.l10;


public class Solution {

    //assume the input is always valid
    public boolean isMatch(String s, int i, String p, int j) {
        if (i == s.length())
        {
            while (j + 1 < p.length() && p.charAt(j+1) == '*')
                j += 2;
            return j == p.length();
        }
        else if (j == p.length()) {
            return false;
        }
        // check the next ch if it's a '*'
        else if (j + 1 < p.length() && p.charAt(j + 1) == '*')
        {

            if (isMatch(s, i, p, j + 2))
                return true;

            while (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'))
            {
                if (isMatch(s, ++i , p, j + 2))
                    return true;
            }
        }
        //next character will not be '*'
        else if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')
        {
            return isMatch(s, i + 1, p, j + 1);
        }


        return false;
    }

    public boolean isMatchRecursive(String s, String p) {
        //compact the strings
        //reduce duplicates
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < p.length(); ++i) {
            if (p.charAt(i) == '*' && p.charAt(i - 1) == '*')
                continue;
            else
                sb.append(p.charAt(i));
        }
        return  isMatch(s, 0, sb.toString(), 0);
    }


    public boolean isMatchDp(String s, String p) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < p.length(); ++i) {
            if (p.charAt(i) == '*' && p.charAt(i - 1) == '*')
                continue;
            else
                sb.append(p.charAt(i));
        }

        p = sb.toString();
        int lengthS = s.length(), lengthP = p.length();
        boolean [] dp = new boolean[1 + lengthP];
        dp[0] = true;
        for (int i = 1; i < dp.length; ++i) {
            if (p.charAt(i - 1) == '*') {
                if (dp[i - 2])
                    dp[i] = true;
                else
                    break;
            }
        }

        for (int i = 1; i <= lengthS; ++i) {
            boolean diag = dp[0];
            dp[0] = false;
            for (int j = 1; j <= lengthP; ++j) {
                boolean tmp = dp[j];
                if (p.charAt(j-1) != '*') {
                    dp[j] = diag && (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.');
                } else {
                    dp[j] = dp[j-2] || (tmp && (s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.'));
                }
                diag = tmp;
            }
        }
        return dp[lengthP];
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        String ss = "a", pp = "ab*";
        System.out.println(s.isMatchDp(ss, pp));
    }
}
