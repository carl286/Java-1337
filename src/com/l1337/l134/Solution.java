package com.l1337.l134;


public class Solution {

    // kind of tricky to make it O(n)
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas.length <= 1)
            return 0;

        //a quick check would be total sum directly...

        for (int i = 0; i < gas.length; ++i)
        {
            //check each node
            int j = 0;
            int delta = 0;
            while (j < gas.length && delta >= 0)
            {
                delta += (gas[(i + j) % gas.length] - cost[(i + j) %gas.length]);
                ++j;
            }
            if (j == gas.length)
                return i;
        }

        return -1;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
