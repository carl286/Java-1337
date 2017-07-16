package com.l1337.l168;


public class Solution {

//    https://leetcode.com/submissions/detail/19084981/
    public String convertToTitle(int n) {
        // assume n i positive here.
        StringBuilder sb = new StringBuilder();
        while (n > 0)
        {
            --n;
            int c = n % 26;
            //System.out.println(String.format("%c",'a' + c));
            sb.append(String.format("%c",'a' + c));
            n /= 26;
        }

        sb.reverse();
        return sb.toString();
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.convertToTitle(27));
    }
}
