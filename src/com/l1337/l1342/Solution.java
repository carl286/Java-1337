package com.l1337.l1342;


public class Solution {

    public int numberOfSteps(int num) {
        int steps = 0;
        while (num != 0)
        {
            if ((num & 0x01) == 0)
                num >>= 1;
            else
                num -= 1;
            ++steps;
        }
        return steps;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
