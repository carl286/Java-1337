package com.l1337.l372;

//https://leetcode.com/submissions/detail/72975421/
public class Solution {

//    https://leetcode.com/problems/super-pow/
//    372. Super Pow

//    https://www.hrwhisper.me/leetcode-super-pow/
//    http://bookshadow.com/weblog/2016/07/07/leetcode-super-pow/
//    http://www.cnblogs.com/grandyang/p/5651982.html
//    https://en.wikipedia.org/wiki/Modular_exponentiation
    public int superPow(int a, int[] b) {
        if (b.length == 0)
            return 1;
        int m = 1337;
        a %= m;

        long ret = 1;
        long acc = a;
        for (int i = b.length - 1; i >= 0 && ret != 0; --i) {
            ret = (ret * helperModel(acc, b[i], m)) % m;
            acc = helperModel(acc, 10, m);
        }
        return (int)ret;
    }

    //You can use loop instead of recursion for below code...
    long helperModel(long a, int power, int m) {
        if (a == 0)
            return 0;
        if (power == 0)
            return 1;
        if (a >= m)
            a %= m;
        long ret = helperModel(a * a, power / 2, m);
        if ((power & 0x01) != 0) {//odd
            return (ret * a) % m;
        } else {
            return ret;
        }
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
        int a = 2147483647;
        int [] b = {2,0,0};
        System.out.println(s.superPow(a,b));
    }
}
