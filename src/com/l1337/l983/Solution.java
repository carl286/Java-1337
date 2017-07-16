package com.l1337.l983;


public class Solution {

    private int [] range = new int [] {1,7,30};

    public int mincostTicketsRecursive(int[] days, int index, int[] costs) {
        if (index >= days.length)
            return 0;

        //try different cost
        int minCost = Integer.MAX_VALUE;
        for(int i = 0; i < costs.length; ++i)
        {
            int localCost = costs[i];
            //find the right indexes...
            int coveredDays = days[index] + range[i] - 1;
            int nextIndex = index;
            while (nextIndex < days.length && days[nextIndex] <= coveredDays)
                ++nextIndex;
            int localRemainingCost = mincostTicketsRecursive(days, nextIndex, costs);
//            System.out.println("nextIndex: "+ nextIndex + "\t" + "coveredDays: " + coveredDays + "\tlocalCost:" + localCost + "\tlocalRemainingCost:" + localRemainingCost );
//            System.out.println("Comparion: minCost: " + minCost + "\tlocals: " + (localCost + localRemainingCost) + "\tresult: " + (minCost > localCost + localRemainingCost));
            minCost = Math.min(minCost, localCost + localRemainingCost);
        }

        return minCost;
    }

    public int mincostTicketsRecursiveMemo(int[] days, int index, int [] memo, int[] costs) {
        if (index >= days.length)
            return 0;

        if (memo[index] != 0)
            return memo[index];

        //try different cost
        int minCost = Integer.MAX_VALUE;
        for(int i = 0; i < costs.length; ++i)
        {
            int localCost = costs[i];
            //find the right indexes...
            int coveredDays = days[index] + range[i] - 1;
            int nextIndex = index;
            while (nextIndex < days.length && days[nextIndex] <= coveredDays)
                ++nextIndex;
            int localRemainingCost = mincostTicketsRecursiveMemo(days, nextIndex, memo, costs);
//            System.out.println("nextIndex: "+ nextIndex + "\t" + "coveredDays: " + coveredDays + "\tlocalCost:" + localCost + "\tlocalRemainingCost:" + localRemainingCost );
//            System.out.println("Comparion: minCost: " + minCost + "\tlocals: " + (localCost + localRemainingCost) + "\tresult: " + (minCost > localCost + localRemainingCost));
            minCost = Math.min(minCost, localCost + localRemainingCost);
        }

        memo[index] = minCost;
        return memo[index];
    }

    public int mincostTicketsRecursive(int[] days, int[] costs) {

        return mincostTicketsRecursive(days, 0, costs);
    }

    public int mincostTicketsRecursiveMemo(int[] days, int[] costs) {

        return mincostTicketsRecursiveMemo(days, 0, new int [days.length], costs);
    }

    public int mincostTicketsDp(int[] days, int[] costs) {
        int [] dp = new int [days.length];
        for(int i = days.length - 1; i >= 0; --i)
        {
            //try fill dp[i]
            dp[i] = Integer.MAX_VALUE;
            for(int k = 0; k < costs.length; ++k)
            {
                int localCost = costs[k];
                int j = i;
                int coveredDays = days[i] + range[k] - 1;
                while (j < days.length && days[j] <= coveredDays)
                    ++j;
                dp[i] = Math.min(dp[i], localCost + (j == days.length ? 0 : dp[j]));
            }
        }

        return dp[0];
    }

//    https://leetcode.com/problems/minimum-cost-for-tickets/discuss/226659/Two-DP-solutions-with-pictures
    public int mincostTickets(int[] days, int[] costs) {

        return -1;
    }

//    https://leetcode.com/problems/minimum-cost-for-tickets/discuss/226670/Java-DP-Solution-with-explanation-O(n)
    public static void main(String [] args) {
        Solution s = new Solution();
        //int [] days = new int [] {1,4,6,7,8,20};

        int [] days = new int [] {1,4,6,7,8, 20};
        int [] costs = new int [] {2,7,15};
        System.out.println(s.mincostTicketsRecursive(days, costs));
    }
}

