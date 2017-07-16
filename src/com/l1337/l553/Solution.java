package com.l1337.l553;


public class Solution {

    public String optimalDivision(int[] nums) {
        if (nums.length <= 0)
            return "";

        //I think we can remove below....
        if (nums.length == 1)
            return Integer.toString(nums[0]);

        float [][] max = new float [nums.length][];
        float [][] min = new float [nums.length][];

        String [][] maxS = new String [nums.length][];
        String [][] minS = new String [nums.length][];


        for (int i = 0; i < nums.length; ++i) {
            max[i] = new float [nums.length];
            min[i] = new float [nums.length];
            maxS[i] = new String[nums.length];
            minS[i] = new String[nums.length];
            max[i][i] = min[i][i] = nums[i];
            maxS[i][i] = minS[i][i] = Integer.toString(nums[i]);
        }

        for (int l = 2; l <= nums.length; ++l) {
            for (int i = 0; i + l - 1< nums.length; ++i) {
                //figure out range i to i + l - 1
                //at minimum
                int j = i + l - 1;
                min[i][j] = min[i][j-1] / nums[j];
                max[i][j] = max[i][j-1] / nums[j];
                minS[i][j] = minS[i][j-1] + "/" + nums[j];
                maxS[i][j] = maxS[i][j-1] + "/" + nums[j];

                //do a range check
                for (int k = i + 1; k < j; ++k) {
//                    System.out.println(i + "\t" + j + "\t" + min[i][j] + "\t" + min[i][k-1] + "\t" + max[k][j] + "\t" + nums[j]);
                    float cache = min[i][k-1]/(max[k][j-1]/nums[j]);
                    if (cache < min[i][j]) {
                        min[i][j] = cache;

                        minS[i][j] = minS[i][k-1] + "/" + "(" + maxS[k][j-1] + "/" + nums[j] + ")";
                    }

//                    System.out.println(i + "\t" + j + "\t" + min[i][j] + "\t" + min[i][k-1] + "\t" + max[k][j] + "\t" + nums[j]);
                    cache = max[i][k-1]/(min[k][j-1]/nums[j]);
                    if (cache > max[i][j]) {
                        max[i][j] = cache;
                        maxS[i][j] = maxS[i][k-1] + "/" + "(" + minS[k][j-1] + "/" + nums[j] + ")";
                    }

                }
            }
        }

//        System.out.println("***MIN***");
//        for (int i = 0; i < nums.length; ++i) {
//            for (int j = 0; j < nums.length; ++j) {
//                System.out.print(min[i][j] + "\t");
//            }
//            System.out.println();
//        }
//
//        System.out.println("***MAX***");
//        for (int i = 0; i < nums.length; ++i) {
//            for (int j = 0; j < nums.length; ++j) {
//                System.out.print(max[i][j] + "\t");
//            }
//            System.out.println();
//        }

//        System.out.println(max[0][nums.length - 1]);
//        System.out.println(maxS[0][nums.length - 1]);
        return maxS[0][nums.length - 1];
    }

//    https://leetcode.com/problems/optimal-division/discuss/101687/Easy-to-understand-simple-O(n)-solution-with-explanation
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.optimalDivision(new int [] {1000,100,10,2}));
//        System.out.println(s.optimalDivision(new int [] {2,3}));
    }
}
