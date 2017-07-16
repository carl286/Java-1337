package com.l1337.l1304;


public class Solution {

    public int[] sumZero(int n) {
        int [] ret = new int[n];
        int half  = n >> 1;
        if ((n & 0x01) == 1)
        {
            ret[0] = 0;
            for(int index = 1, i = 1; i <= half; ++i)
            {
                ret[index++] = i;
                ret[index++] = -i;
            }

        }
        else
        {
            for(int index = 0, i = 1; i <= half; ++i)
            {
                ret[index++] = i;
                ret[index++] = -i;
            }
        }


        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
