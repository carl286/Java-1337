package com.l1337.l875;


public class Solution {

    public int minEatingSpeed(int[] piles, int h) {
        int maxPile = 0;
        for(int i = 0; i < piles.length; ++i)
            if (piles[i] > maxPile)
                maxPile = piles[i];

        long l = 1, r = Integer.MAX_VALUE;
        while (l < r)
        {
            long mid = ((r - l) >> 1) + l;
            long testH = getTime(piles, mid);
            if (testH > h)
            {
                l = mid + 1;
            }
            else
            {
                r = mid;
            }
        }

        return (int)l;
    }

    private long getTime(int [] piles, long k)
    {
        long ret = 0;
        for(int i = 0; i < piles.length; ++i)
        {
            ret += piles[i] / k;
            if (piles[i] % k != 0)
                ret += 1;
        }
        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        int [] piles = new int[]{
        332484035,524908576,855865114,632922376,222257295,690155293,112677673,679580077,337406589,290818316,877337160,901728858,679284947,688210097,692137887,718203285,629455728,941802184
        };
        int h = 823855818;
        System.out.println(s.minEatingSpeed(piles, h));
    }
}
