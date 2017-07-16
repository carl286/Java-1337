package com.l1337.l492;


public class Solution {

//    https://leetcode.com/problems/construct-the-rectangle/discuss/148641/Simple-Java-Solution
    public int[] constructRectangle(int area) {
        //area >= 1
        int [] ret = new int[2];

        int mid = (int) Math.sqrt(area);
        for (int w = mid; w >= 1; --w) {
            int l = area / w;
            if (l * w == area) {
                ret[0] = l;
                ret[1] = w;
                return ret;
            }
        }

        if (mid ==  0) {
            ret[0] = 1;
            ret[1] = 1;
        }
        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        for (int i : s.constructRectangle(9)) {
            System.out.print(i + "\t");
        }
    }
}
