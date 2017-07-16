package com.l1337.l1423;


public class Solution {

    public int maxScore(int[] cardPoints, int k) {
        int total = 0;
        for (int i = 0; i < cardPoints.length; ++i)
            total += cardPoints[i];
        if (k >= cardPoints.length)
        {
            return total;
        }

        int count = cardPoints.length - k;
        int minInnerSum = 0, acc = 0;
        for (int i = 0; i < count; ++i)
            acc += cardPoints[i];
        minInnerSum = acc;
        for (int i = count; i < cardPoints.length; ++i)
        {
            acc = acc - cardPoints[i-count] + cardPoints[i];
            minInnerSum = Math.min(acc, minInnerSum);
        }
        return total - minInnerSum;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
