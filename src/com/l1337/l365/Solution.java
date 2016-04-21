package com.l1337.l365;


public class Solution {

    public int gcd(int x, int y) {
        //x >= y >= 1
        x %= y;
        if (x == 0)
            return y;
        return gcd(y, x);
    }

//    https://www.hrwhisper.me/leetcode-water-jug-problem/
//    https://zh.wikipedia.org/wiki/%E8%B2%9D%E7%A5%96%E7%AD%89%E5%BC%8F
//    https://discuss.leetcode.com/topic/49751/clear-explanation-of-why-using-gcd
//    https://en.wikipedia.org/wiki/B%C3%A9zout%27s_identity
    public boolean canMeasureWater(int x, int y, int z) {
        if (z < 0 || x + y < z)
            return false;
        if (z == 0)
            return true;
        if (x == 0 && y == 0)
            return false;
        if (x == 0)
            return z % y == 0;
        if (y == 0)
            return z % x == 0;

        //assume x>0, y>0
        int gcd;
        if (x < y)
            gcd = gcd(y, x);
        else
            gcd = gcd(x, y);

        return z % gcd == 0;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.canMeasureWater(3,5,4));
        System.out.println(s.canMeasureWater(2,6,4));
    }
}
