package com.l1337.l482;


public class Solution {

//    https://leetcode.com/problems/license-key-formatting/discuss/137810/My-Java-solution-38ms
    //You can do just one loop
    public String licenseKeyFormatting(String S, int K) {
        String[] strings = S.split("-");
        StringBuilder sb = new StringBuilder();
        for (char c : S.toCharArray()) {
            if (c == '-')
                continue;
            else
                sb.append(Character.toUpperCase(c));
        }

        StringBuilder retSb = new StringBuilder();
        if (sb.length() <= K)
            return sb.toString();

        int remainder = sb.length() % K;
        if (remainder == 0)
            remainder = K;
        retSb.append(sb.substring(0, remainder));
        for (int start = remainder; start < sb.length(); start+= K) {
            retSb.append('-');
            retSb.append(sb.substring(start, start + K));
        }

        return retSb.toString();
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
