package com.l1337.l660;


public class Solution {

//    https://leetcode.com/articles/remove-9/
//    https://blog.csdn.net/huanghanqian/article/details/77187600
//    http://www.cnblogs.com/grandyang/p/8261714.html
    public int newInteger(int n) {
        return Integer.parseInt(Integer.toString(n, 9));
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
