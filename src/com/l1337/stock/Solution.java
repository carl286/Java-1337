package com.l1337.stock;


public class Solution {


//    121. Best Time to Buy and Sell Stock
    //    https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
    public int maxProfitI(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;
        int ret = 0;
        int curMin = prices[0];
        for (int i = 1; i < prices.length; ++i) {
            ret = Math.max(ret, prices[i] - curMin);
            curMin = Math.min(curMin, prices[i]);
        }
        return ret;
    }


//    122. Best Time to Buy and Sell Stock II
//    https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
    public int maxProfitII(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;

        int ret = 0;
        for (int i = 1; i < prices.length; ++i)
            if (prices[i] > prices[i-1])
                ret += (prices[i] - prices[i-1]);
        return ret;
    }


//    https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
//    188. Best Time to Buy and Sell Stock IV
//    https://leetcode.com/submissions/detail/32607703/
//    https://leetcode.com/submissions/detail/26407388/
//    http://www.jiuzhang.com/solutions/best-time-to-buy-and-sell-stock-iv/
public int maxProfitIV(int k, int[] prices) {
    if (prices == null || prices.length <= 1 || k < 1)
        return 0;

    if (k > prices.length / 2)
        return maxProfitII(prices);

    int [] delta = new int [prices.length-1];
    for (int i = 1; i < prices.length; ++i)
        delta[i-1] = prices[i] - prices[i-1];

    int ret = 0;
    int [] f = new int [prices.length-1];
    ret = f[0] = Math.max(0, delta[0]);
    for (int i = 1; i < f.length; ++i) {
        f[i] = Math.max(0, f[i-1] + delta[i]);
        ret = Math.max(ret, f[i]);
    }

    //f is kind of init for only 1 round and ends with each index
    for (int round = 2; round <= k; ++round) {
        //take from last global g, 1 shift from array (array starts from 0), 1 shift from last round (current round is round)
        int g = f[round-2];
        //from 0 to round - 1, we have at least round elements
        for (int i = round - 1; i < delta.length; ++i) {
            int tmp = f[i];
            f[i] = Math.max(0, delta[i] + Math.max(g, f[i-1]));
            g = Math.max(g, tmp);
            ret = Math.max(f[i],ret);
        }
    }

    return ret;
}

    //You don't have to take it on the ith, you can think it as buy and sell it on the same day.....

    public int maxProfitWithCoolDown(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;
        int s = 0, b = -prices[0], c=0;
        for (int i = 1; i < prices.length; ++i) {
            int ns = b + prices[i];
            int nb = c - prices[i];
            int nc = Math.max(c, s);
            s = Math.max(s,ns);
            b = Math.max(b,nb);
            c = Math.max(c,nc);
        }
        return Math.max(s, Math.max(b,c));
    }


    public static void main(String [] args) {
        Solution s = new Solution();
//        int[] prices = {1, 2, 3, 0, 2};
        int[] prices = {1, 2, 4};
        System.out.println(s.maxProfitWithCoolDown(prices));
    }
}
