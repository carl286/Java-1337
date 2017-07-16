package com.l1337.l1573;


public class Solution {

//    https://leetcode.com/problems/number-of-ways-to-split-a-string/discuss/830775/Java-O(n)-time-and-O(1)-space
    private final int BASE_MODULE = 1000000000+7;
    public int numWays(String s) {
        int n = s.length();
        int [] counts = new int [n];
        counts[0] = s.charAt(0) == '1' ? 1 : 0;
        for(int i = 1; i < s.length(); ++i)
        {
            counts[i] = counts[i-1];
            if (s.charAt(i) == '1')
                ++counts[i];
        }

        int totalsOnes = counts[n-1];
        if (totalsOnes % 3 != 0)
            return 0;
        int eachPart = totalsOnes / 3;
        int secondPart = (eachPart << 1);
        int total = 0;

        int left = findLeft(counts, 0, eachPart);
        int right = findRight(counts, 0, eachPart);
        for(int i = left; i <= right && i + 2 < n; ++i)
        {
            int innerLeft = findLeft(counts, i + 1, secondPart);
            int innerRight = findRight(counts, i + 1, secondPart);
            if (innerRight > 0)
            {
                total = ((Math.min(innerRight, n - 2) - innerLeft + 1) + total) % BASE_MODULE;
            }
            else
            {
                break;
            }
        }
        return total;
    }

    //array is sorted, searching frmo index i, find the left most index whoes values eqauls to target, if not found, return -1;
    private int findLeft(int [] array, int i, int target)
    {
        int left = i - 1, right = array.length;
        while(left + 1 != right)
        {
            int mid = (right - left)/2 + left;
            if (array[mid] < target)
            {
                left = mid;
            }
            else
            {
                right = mid;
            }
        }
        if (right == array.length || array[right] != target)
            return -1;
        return right;
    }

    //array is sorted, searching frmo index i, find the left most index whoes values eqauls to target, if not found, return -1;
    private int findRight(int [] array, int i, int target)
    {
        int left = i - 1, right = array.length;
        while(left + 1 != right)
        {
            int mid = (right - left)/2 + left;
            if (array[mid] <= target)
            {
                left = mid;
            }
            else
            {
                right = mid;
            }
        }
        if (left == i - 1 || array[left] != target)
            return -1;
        return left;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.numWays("0000"));
    }
}
