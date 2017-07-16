package com.l1337.l521;


public class Solution {

    public int findLUSlength(String a, String b) {
        if (a.equals(b))
            return -1;
        else
            return Math.max(a.length(), b.length());
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
