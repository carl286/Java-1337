package com.l1337.l276;

//	There is a fence with n posts, each post can be painted with one of the k colors.
//	You have to paint all the posts such that no more than two adjacent fence posts have the same color.
//	Return the total number of ways you can paint the fence.
//	Note:n and k are non-negative integers.

//276	Paint Fence
public class Solution {

    public int numWays(int n, int k) {
        //Everytime, I just don't understand what the problem is talking about....
        return 0;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}


/*
//	这种给定一个规则，计算有多少种结果的题目一般都是动态规划，因为我们可以从这个规则中得到递推式。
根据题意，不能有超过连续两根柱子是一个颜色，
也就意味着第三根柱子要么根第一个柱子不是一个颜色，要么跟第二根柱子不是一个颜色。
如果不是同一个颜色，计算可能性的时候就要去掉之前的颜色，也就是k-1种可能性。假设dp[1]是第一根柱子及之前涂色的可能性数量，dp[2]是第二根柱子及之前涂色的可能性数量，则dp[3]=(k-1)*dp[1] + (k-1)*dp[2]。
//	递推式有了，下面再讨论下base情况，所有柱子中第一根涂色的方式有k中，第二根涂色的方式则是k*k，因为第二根柱子可以和第一根一样。

	//	设S(n)表示当前杆和前一个杆颜色相同的个数，D(n)表示当前杆和前一个颜色不相同的个数。
//	则递推关系式为：
//	S(n) = D(n - 1)， 即若当前杆和前一个杆颜色相同的个数等于前一个杆和再前一个杆颜色不相同的个数。
//	D(n) = (k - 1) * (D(n - 1) + S(n - 1))，即前一个杆和再前一个杆所有可能的颜色组合，乘以当前杆与前一个杆颜色不相同的个数，即（k - 1）。
//	用两个变量记录D和S，并交替更新即可。
	public int numWays(int n, int k) {
		if (n == 0 || k == 0)
			return 0;
		if (n == 1)
			return k;
		int lastS = k;
		int lastD = k * (k - 1);
		for (int i = 2; i < n; i++) {
			int tempD = (k - 1) * (lastS + lastD);
			lastS = lastD;
			lastD = tempD;
		}
		return lastS + lastD;
	}
 */