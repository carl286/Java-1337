package com.fb.sortedCounts;

public class SortedCounts {

    //each value in count is not empty, the sum in counts is at least k
    //k >= 1
    public long sortedCounts(int [] counts, int k)
    {
        long [][] dp  = new long [k + 1][counts.length];
        for (int kk = 1; kk < dp.length; ++kk)
        {
            for (int i = 0; i < counts.length; ++i)
            {
                //update dp[kk][i]
                for (int j = 1; j < kk && j <= counts[i]; ++j)
                {
                    for (int l = 0; l < i; ++l)
                    {
                        dp[kk][i] += dp[j][l];
                    }
                }
                if (kk <= counts[i])
                    ++dp[kk][i];
            }
        }

        long total = 0;
        for (int i = 0; i < counts.length; ++i)
        {
            total += dp[k][i];
        }
        return total;
    }
    public static void main(String [] args) {
        SortedCounts s = new SortedCounts();
        System.out.println(s.sortedCounts(new int [] {3,3,3}, 3));
    }
}
