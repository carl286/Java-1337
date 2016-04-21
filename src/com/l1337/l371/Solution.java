package com.l1337.l371;


public class Solution {

    public int getSum(int a, int b) {
        int ret = 0;
        long carryon = 0;
        int mask = 1;
        while ( (a & ~(mask - 1)) != 0 || (b & ~(mask - 1)) != 0 || carryon != 0) {
            int c = (a & mask);
            int d = (b & mask);

//            System.out.println(Integer.toHexString(a));
//            System.out.println(Integer.toHexString(b));
//            System.out.println(Integer.toHexString(c));
//            System.out.println(Integer.toHexString(d));
//            System.out.println(Long.toHexString(carryon));
//            System.out.println(Integer.toHexString(ret));
//            System.out.println("***********");
            long sum = (c & d & carryon) | (~c & d & ~carryon) | (c & ~d & ~carryon) | (~c & ~d & carryon);
            carryon = (c & d) | (carryon & c) | (carryon & d);
            carryon <<= 1;
            ret |= sum;
            mask <<= 1;
        }
        return ret;
    }


//    http://www.geeksforgeeks.org/add-two-numbers-without-using-arithmetic-operators/
//    http://bookshadow.com/weblog/2016/06/30/leetcode-sum-of-two-integers/
//    a ^ b 直接算出a + b 每位上%2的结果， 进位的话可以直接 (a & b)<<1得到（只有两个位均为1才会进位嘛，左移表示进到下一位啊）

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
        int a = -1, b = 1;
        System.out.println(s.getSum(a,b));
    }
}
