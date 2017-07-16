package com.l1337.l825;


public class Solution {

    private boolean isFriend(int ageA, int ageB)
    {
        return !((ageB > ageA) || (ageB > 100 && ageA < 100) || (ageB <= 0.5 * ageA + 7));
    }
    public int numFriendRequests(int[] ages) {
        int ret = 0;
        for(int i = 0; i < ages.length; ++i)
        {
            for(int j = i + 1; j < ages.length; ++j)
            {
                if (isFriend(ages[i], ages[j]))
                    ++ret;
                if (isFriend(ages[j], ages[i]))
                    ++ret;
            }
        }

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
