package com.l1337.l483;


public class Solution {

//    https://leetcode.com/problems/smallest-good-base/discuss/96587/Python-solution-with-detailed-mathematical-explanation-and-derivation
//    https://leetcode.com/problems/smallest-good-base/discuss/96593/Concise-C++-Binary-Search-solution

    /**
     class Excel {
     public:
     string smallestGoodBase(string n) {
     using ull = unsigned long long;
     // num - 1 is the largest possible base
     ull num = stoll(n);
     // this loop will iterate length from max possible value to min value
     // when base == 2, we have longest length of '1'
     // 2^0 + 2^1 + ... + 2^(len - 1) == num -> 2^len == num + 1 -> len = log(num + 1) base on 2
     // log(num + 1) / log(2) == log (num + 1) base on 2
     for (int len = log(num + 1) / log(2); len >= 2; len--) {
     // use binary search to find possible base
     // b^0 + b^1 + .... + b^(len - 1) == num ->
     // b^(len - 1) <= num + 1 ->
     // b <= pow(num + 1, 1.0 / (len - 1))
     ull l = 2, r = pow(num + 1, 1.0 / (len - 1)) + 1;
     while (l < r) {
     ull sum = 0, base = l + (r - l) / 2, val = 1;
     for (int i = 0; i < len; i++, val *= base)
     sum += val;
     if (sum == num)
     return to_string(base);
     else if (sum < num)
     l = base + 1;
     else
     r = base;
     }
     }
     return to_string(num - 1);
     }
     };
     */
//    https://leetcode.com/problems/smallest-good-base/discuss/96591/Java-O((logn)2)-binary-search-solution
    public String smallestGoodBase(String n) {
        long num = Long.valueOf(n);

        for (int m = (int)(Math.log(num + 1) / Math.log(2)); m >= 2; m--) {
            long l = (long)(Math.pow(num + 1, 1.0 / m));
            long r = (long)(Math.pow(num, 1.0 / (m - 1)));

            while (l <= r) {
                long k = l + ((r - l) >> 1);
                long f = 0L;
                for (int i = 0; i < m; i++, f = f * k + 1);

                if (num == f) {
                    return String.valueOf(k);
                } else if (num < f) {
                    r = k - 1;
                } else {
                    l = k + 1;
                }
            }
        }

        //I don't think below line should got to run unless we have runoff issues for the upper limit of m.
        return String.valueOf(num - 1);
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.smallestGoodBase("23"));
    }
}
