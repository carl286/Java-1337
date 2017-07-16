package com.l1337.knapsack;

public class PackV1 {
//    https://www.kancloud.cn/kancloud/pack/70125

    /*
    0/1 v1
    有N 件物品和一个容量为V 的背包。放入第i 件物品耗费的费用是Ci
1，得到的
价值是Wi。求解将哪些物品装入背包可使价值总和最大。

现有n件物品和一个容量为c的背包。第i件物品的重量是重量为w[i]，价值是v[i]。已知对于一件物品必须选择取（用1表示）或者不取（用0表示)
且每件物品只能被取一次（这就是“0-1”的含义）。求放置哪些物品进背包，可使这些物品的重量总和不超过背包容量，且价值总和最大。
     */
    // max(cost) is <= capacity
    // assume capacity > 0, cost_i >0, value_i > 0
    public int pack(int [] cost, int [] value, int capacity)
    {
        int dp [] = new int [1 + capacity];
        //init
//        for(int i = 0; i < cost.length; ++i)
//        {
//            dp[cost[i]] = Math.max(value[i], dp[cost[i]]);
//        }

        return dp[capacity];
    }
    public static void main(String [] args) {
        PackV1 s = new PackV1();
        int [] cost = new int [] {2,2,6,5,4};
        int [] value = new int [] {2,2,6,5,4};
        int capacity = 10;
        System.out.println(s.pack(cost, value, capacity));
    }
}
