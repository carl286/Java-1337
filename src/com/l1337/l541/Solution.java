package com.l1337.l541;


public class Solution {

//    https://leetcode.com/problems/reverse-string-ii/discuss/100866/Java-Concise-Solution
    public String reverseStr(String s, int k) {
        if (k == 1)
            return s;
        StringBuilder sb = new StringBuilder();
        int cur = 0;
        while (cur < s.length()) {
            int end = Math.min(cur + k, s.length()) - 1;
            //reverse between cur, and end
            for (int j = end; j >= cur; --j)
                sb.append(s.charAt(j));

            cur = end + 1;
            end = Math.min(cur + k, s.length());
            for (int j = cur; j < end; ++j)
                sb.append(s.charAt(j));

            //copy between [cur, end)

            cur = end;
        }

        return sb.toString();
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
