package com.l1337.l896;


public class Solution {

    public boolean isMonotonic(int[] A) {
        int i = 0;
        boolean isInOrder = true;
        while (i + 1< A.length)
        {
            if (A[i] <= A[i+1]) {
                ++i;
            }
            else {
                isInOrder = false;
                break;
            }
        }

        if(isInOrder)
            return true;

        isInOrder = true;
        i = 0;
        while (i + 1< A.length)
        {
            if (A[i] >= A[i+1]) {
                ++i;
            }
            else {
                isInOrder = false;
                break;
            }
        }

        if(isInOrder)
            return true;

        return false;
    }

//    https://leetcode.com/problems/monotonic-array/solution/
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
