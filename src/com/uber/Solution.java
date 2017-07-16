package com.uber;


import java.util.ArrayDeque;
import java.util.Stack;

public class Solution {

    /*
    The matching should cover the entire input string (not partial).. 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
The function prototype should be:
bool isMatch(String str, String patter)
Some examples:.
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa","a{1,3}") → true
isMatch("aaa","a{1,3}") → false
isMatch("ab","a{1,3}b{1,3}") → true
isMatch("abc","a{1,3}b{1,3}c") → true
isMatch("abbc","a{1,3}b{1,2}c") → false
isMatch("acbac","a{1,3}b{1,3}c") → false
isMatch("abcc","a{1,3}b{1,3}cc{1,3}") → true
range is like [a,b), assume all inputs are valid...

     */
    boolean isMathc(String a, String b) {

        return false;
    }

    //will {0,0}, {3,3}, {0,1} ??

    /*
    Given an array A[] of integers and an integer m representing the size of sub-array.
We need to return maximum among the minimum elements of m-size sub arrays in A.
     */
    //assume m <= A.length
    public int maxOfMininArray(int A [], int m)
    {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        int ret = Integer.MIN_VALUE;
        for(int i = 0, start = 0; i < A.length; ++i)
        {
            if (i - start >= m)
            {
                if (queue.peekFirst() == A[start])
                {
                    queue.removeFirst();
                }
                ++start;
            }
            while (!queue.isEmpty() && queue.peekLast() > A[i])
            {
                queue.removeLast();
            }
            queue.addLast(A[i]);
            ret = Math.max(ret, queue.peekFirst());
        }
        return ret;
    }

    /*
    Given two arrays A[] and B[] containing positive integers and an integer 'd'.
we need to return the count of elements,A[i], in A[] that satisfies below condition
| A[i] - B[j] | > d (0<=j<n where n is size of B).
e.g.
Input : A = [7, 6, 9] B = [13, 1, 4] and d = 3
Output : 1
Explanation : For A[i] = 7, Difference | A[i] - B[j] | is greater than d for B[j] = 13, 1 but equal to 3 for B[j] = 4. Hence, 7 does not qualify.
Similary for 6, the differences are [7, 5, 2]. since 2 is less equal to 3 hence this element does not qualify as well.
Whereas for 9, the differences are [4, 8, 5]. here difference with each element of B is greater than d. So we have found 1 such element.
So the output would be 1.


     */
    public int countElements(int A[], int B[], int d)
    {
//        https://github.com/TomerRabani/DSA-coding-interview-questions-oa/blob/main/array%20rank%3B%20count%20array%20%3E%3D%20d%20away%20all%20in%20b.java
        return -1;
    }

    //this problem is stupid, how come you can sell more than you have bought
    public int maxRevenueFromStocks(int [] prices, int [] algoAn, int k)
    {
        int [] originalPrices = new int [prices.length + 1];
        int [] originalRevenue = new int [1 + algoAn.length];
        for (int i = 0; i < prices.length; ++i)
        {
            originalPrices[i+1] = originalPrices[i] + prices[i];
            originalRevenue[i+1] = originalRevenue[i] + (algoAn[i] == 1 ? prices[i]: -prices[i]);
        }

        int ret = originalRevenue[algoAn.length];

        for (int i = 0; i + k <= algoAn.length; ++i)
        {
            //mark from i till i + k as sell
            ret = Math.max(ret,
                    originalRevenue[algoAn.length] -
                            originalRevenue[i+k] +
                            originalRevenue[i] +
                            originalPrices[i+k] -
                            originalPrices[i]);
        }

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
//        int A [] = new int [] {10,20,30,50,10,70,30};
//        for (int m = 1; m <= 4; ++m)
//        System.out.println(s.maxOfMininArray(A, m));
        System.out.println(s.maxRevenueFromStocks(new int [] {2, 4, 1, 5, 2, 6, 7},
                new int [] {0,1,0,0,1,0,0}, 4));
    }
}
