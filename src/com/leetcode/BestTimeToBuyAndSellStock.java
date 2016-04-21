package com.leetcode;

/**
 * Created by Ke.Liu on 5/22/16.
 */
public class BestTimeToBuyAndSellStock {


//    https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
//    121. Best Time to Buy and Sell Stock
//Say you have an array for which the ith element is the price of a given stock on day i.
//If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
    public int maxProfitI(int[] prices) {
        if(prices == null || prices.length <= 1)
            return 0;
        int length = prices.length;
        int left = prices[0];
        int ret = 0;
        for (int i = 1; i < length; i++) {
            left = Math.min(prices[i], left);
            ret = Math.max(ret, prices[i]- left);
        }


        return ret;
    }

//    https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
//    122. Best Time to Buy and Sell Stock II
//Say you have an array for which the ith element is the price of a given stock on day i.
//    Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
    public int maxProfitII(int[] prices) {
        if(prices == null || prices.length <= 1)
            return 0;

        int ret = 0;
        for (int i = 1; i < prices.length; i++)
            ret += Math.max(0, prices[i] - prices[i-1]);
        return ret;
    }

//    https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
//    123. Best Time to Buy and Sell Stock III
//Design an algorithm to find the maximum profit. You may complete at most two transactions.
//You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

    private int maxProfitI(int[] prices, int start, int end) {
        if (end <= start)
            return 0;
        int ret = 0;
        int left = prices[start];
        for (int i = start + 1; i <= end; i++) {
            left = Math.min(left, prices[i]);
            ret = Math.max(ret, prices[i] - left);
        }

        return ret;
    }
    public int maxProfitIII(int[] prices) {
        if(prices == null || prices.length <= 1)
            return 0;

        //we can handle k transactions later, use maxProfitI for now.
        //TLE below....
        //You should reuse previous results....
        /*
        int ret = maxProfitI(prices, 0, prices.length - 1);
        for (int i = 0; i < prices.length; i++) {
            ret = Math.max(maxProfitI(prices, 0, i) + maxProfitI(prices, i+1, prices.length - 1), ret);
        }
        return ret;
        */

        int ret = 0;
        int length = prices.length;
        int [] mins = new int[length];
        mins[0] = prices[0];
        for (int i = 1; i < length; i++)
            mins[i] = Math.min(mins[i-1], prices[i]);
        int maxs = prices[length-1];
        int right = 0;
        ret = prices[length-1] - mins[length-1];
//        for (int i = 0; i < length; i++)
//            System.out.print(mins[i] + "\t");
//        System.out.println();
        for (int i = length-1; i > 0; i--) {
            maxs = Math.max(maxs, prices[i]);
            right = Math.max(right, maxs - prices[i]);
            ret = Math.max(ret, right + (prices[i-1] - mins[i-1]));
        }

        return ret;
    }


//    https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
//    188. Best Time to Buy and Sell Stock IV
    public int maxProfitIV(int k, int[] prices) {
        if(prices == null || prices.length <= 1 || k <= 0)
            return 0;

        int ret = 0;
        int length = prices.length;
        if (k >= length / 2)
            return maxProfitII(prices);

        int [] delta = new int[length-1];
        for (int i = 1; i < length; i++)
            delta[i-1] = prices[i] - prices[i-1];
//        https://leetcode.com/submissions/detail/32607703/
//        https://leetcode.com/submissions/detail/26407388/

//        local[i][j] = max(global[i-1][j-1] + max(diff,0), local[i-1][j]+diff)
//        global[i][j] = max(local[i][j], global[i-1][j])
//        The local array tracks maximum profit of j transactions & the last transaction is on ith day.
// The global array tracks the maximum profit of j transactions until ith day.
//        http://liangjiabin.com/blog/2015/04/leetcode-best-time-to-buy-and-sell-stock.html
//        local[j] = Math.max(global[j-1]+(diff>0?diff:0), local[j]+diff);
//        global[j] = Math.max(local[j],global[j]);
//        https://gist.github.com/ElninoFong/d08051d98e634991cd93
        return ret;
    }


    public int maxProfitWithCooldown(int[] prices) {
        if(prices == null || prices.length <= 1)
            return 0;
        int ret = 0;

        int length = prices.length;
        /* Below is wrong... Because it could not figure sell or buy...
        int [] transactions = new int [length];
        int [] downs = new int [length];
        int [] g = new int [length];
        transactions[0] = -prices[0];
        downs[0] = 0;
        g[0] = Math.max(downs[0], transactions[0]);

        for (int i = 1; i < length; i++) {
            transactions[i] = Math.max(transactions[i-1] + prices[i], downs[i-1] - prices[i]);
            downs[i] = Math.max(downs[i-1], g[i-1]);
            g[i] = Math.max(g[i-1], Math.max(transactions[i], downs[i]));
        }

        return g[length-1];
        */


        /* V1
        int [] buys = new int [length];
        int [] sells = new int [length];
        int [] downs = new int [length];
        buys[0] = -prices[0];
        int maxBuy = buys[0];
        sells[0] = 0;
        downs[0] = 0;
        */

        //v2
        //See v1 why we have this code.
        int lastSell = 0;
        int lastDown = 0;
        int maxBuy = -prices[0];

        for (int i = 1; i < length; i++) {
            /* why below is wrong?? Sell should be able to choose from all historical to do this..
            buys[i] = downs[i-1] - prices[i];
            sells[i] = buys[i-1] + prices[i];
            downs[i] = Math.max(downs[i-1], sells[i]);
            ret = Math.max(ret, Math.max(downs[i], Math.max(buys[i], sells[i])));
            */


            /* We should optimize further V1
            buys[i] = downs[i-1] - prices[i];
            sells[i] = maxBuy + prices[i];
            downs[i] = Math.max(downs[i-1], sells[i-1]);
            maxBuy = Math.max(maxBuy, buys[i]);
            ret = Math.max(ret, Math.max(downs[i], Math.max(buys[i], sells[i])));
            */

            //starts V2
            int curSell = maxBuy + prices[i];
            int curDown = Math.max(lastDown, lastSell);
            maxBuy = Math.max(maxBuy, lastDown - prices[i]);
            lastSell = curSell;
            lastDown = curDown;
            ret = Math.max(ret, Math.max(lastDown, Math.max(lastSell, maxBuy)));
        }

        return ret;
    }
    public static void main(String [] args) {

        BestTimeToBuyAndSellStock s = new BestTimeToBuyAndSellStock();
//        int [] prices = {3,2,6,5,0,3};
//        int [] prices = {1,2,4};
//        System.out.println(s.maxProfitIII(prices));

        int [] prices = {1, 2, 3, 0, 2};
        System.out.println(s.maxProfitWithCooldown(prices));

    }
}
