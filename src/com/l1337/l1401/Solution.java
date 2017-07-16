package com.l1337.l1401;


public class Solution {

    private boolean isInCircle(int radius_power, int x_center, int y_center, int x, int y)
    {
        int d1 = x - x_center, d2 = y - y_center;
        return d1 * d1 + d2 * d2 <= radius_power;
    }

//    https://leetcode.com/problems/circle-and-rectangle-overlapping/discuss/563463/C%2B%2B-with-simple-explanation
//    https://leetcode.com/problems/circle-and-rectangle-overlapping/discuss/563441/JAVA-compare-distance-between-radius-and-closest-point-on-rectangle-to-circle
    public boolean checkOverlap(int radius, int x_center, int y_center, int x1, int y1, int x2, int y2) {
        int x3 = x_center - radius, y3 = y_center - radius;
        int x4 = x_center + radius, y4 = y_center + radius;

        if (x3 > x2 || x1 > x4 || y3 > y2 || y1 > y4)
            return false;

        int x5 = Math.max(x1,x3);
        int y5 = Math.max(y1,y3);
        int x6 = Math.min(x2,x4);
        int y6 = Math.min(y2,y4);

        //check over lap points.
        int radius_power = radius * radius;

        for (int i = x5; i <= x6; ++i)
            for (int j = y5; j <= y6; ++j)
                if (isInCircle(radius_power, x_center, y_center, i,j))
                    return true;
        return false;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
