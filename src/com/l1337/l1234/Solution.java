package com.l1337.l1234;


public class Solution {

//    https://leetcode.com/problems/replace-the-substring-for-balanced-string/discuss/408978/javacpython-sliding-window/367697
    public int balancedString(String s) {
        int[] count = new int[128];
        int n = s.length(), res = n, i = 0, k = n / 4;
        for (int j = 0; j < n; ++j) {
            ++count[s.charAt(j)];
        }
        for (int j = 0; j < n; ++j) {
            --count[s.charAt(j)];
            while (i < n && count['Q'] <= k && count['W'] <= k && count['E'] <= k && count['R'] <= k) {
                res = Math.min(res, j - i + 1);
                ++count[s.charAt(i++)];
            }
        }
        return res;
    }

//
//    https://leetcode.com/problems/replace-the-substring-for-balanced-string/discuss/884039/Python3-sliding-window-with-explanation
//    find out which letters have extra numbers in the string
//    find out the shortest substring that contains those extra letters

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.balancedString("WWEQERQWQWWRWWERQWEQ"));
    }
}
