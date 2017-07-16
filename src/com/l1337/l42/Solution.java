package com.l1337.l42;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public int trap(int[] height) {
        if (height.length <= 2)
            return 0;

        int [] left = new int [height.length];
        int [] right = new int [height.length];

        left[0] = height[0];
        for (int i = 1; i < height.length - 1; ++i) {
            left[i] = Math.max(left[i-1], height[i-1]);
            // System.out.print(i + "X" + left[i] + "\t");
        }
        // System.out.println();

        right[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i > 0; --i) {
            right[i] = Math.max(right[i+1], height[i+1]);
            // System.out.print(i + "X" + right[i] + "\t");
        }
        // System.out.println();

        int ret = 0;
        for (int i = 1; i < height.length - 1; ++i) {
            ret += Math.max(0, Math.min(left[i], right[i]) - height[i]);
            // System.out.print(i + "X" + ret + "\t");
        }
        // System.out.println();
        return ret;
    }

    public int trapApril6_21(int[] height) {
        if (height.length < 3)
            return 0;
        int maxIndex = 0;
        for(int i = 1; i < height.length; ++i)
        {
            if (height[i] > height[maxIndex])
                maxIndex = i;
        }

        int ret = 0;
        for(int leftBoarder = 0, i = 1; i < maxIndex; ++i)
        {
            if (height[i] > height[leftBoarder])
                leftBoarder = i;
            else
                ret += (height[leftBoarder] - height[i]);
        }

        for(int rightBoarder = height.length - 1, i = height.length - 2; i > maxIndex; --i)
        {
            if (height[i] > height[rightBoarder])
                rightBoarder = i;
            else
                ret += (height[rightBoarder] - height[i]);

        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int [] hums = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(s.trapApril6_21(hums));

    }
}
