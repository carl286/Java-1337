package com.l1337.l714;


public class Solution {

//    https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/116117/Python-greedy-solution-with-a-little-trick-(beats-98)-O(n)-time-O(1)-space
//    https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108884/JavaC++-Clean-Code-(DPGreedy)
//    https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108871/2-solutions-2-states-DP-solutions-clear-explanation!
//    https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/160964/java-Using-State-Machine-like-stock-III


//    https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/most-consistent-ways-of-dealing-with-the-series-of-stock-problems
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int len = prices.length;
        int[] hold = new int[len];     //Till day i, the max profit is hold[i] if I hold the stock.
        int[] notHold = new int[len];  //Till day i, the max profit is notHold[i] if I do not hold the stock.

        hold[0] = -prices[0];
        notHold[0] = 0;

        for (int i = 1; i < prices.length; i++) {
            hold[i] = Math.max(hold[i - 1], notHold[i - 1] - prices[i]);
            notHold[i] = Math.max(notHold[i - 1], hold[i - 1] - fee + prices[i]);
        }

        return notHold[len - 1];
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.maxProfit(new int []{1,3,2,8,4,9}, 2));
    }
}
