package com.l1337.l634;


public class Solution {
    /*
    In combinatorial mathematics, a derangement is a permutation of the elements of a set, such that no element appears in its original position.

There's originally an array consisting of n integers from 1 to n in
 ascending order, you need to find the number of derangement it can generate.

Also, since the answer may be very large, you should return the output mod 109 + 7.

Example 1:
Input: 3
Output: 2
Explanation: The original array is [1,2,3]. The two derangements are [2,3,1] and [3,1,2].

1）把第n个元素放在某一个位置，比如位置k，那么一共有(n-1)中放法。
2）放编号为k的元素，此时有两种情况：a）把k放到位置n，那么对于剩下的n-2个元素，就一共有D(n-2)种放法；b）第k个元素不放在位置n，这时k连同其余的n-2个元素都各有一个位置不能放，所以有D(n-1)种放法。
因此，D(n) = (n - 1) (D(n - 2) + D(n - 1))，特别地，有D(1) = 0, D(2) = 1。

 you need make sure it's really 1-1

Note:n is in the range of [1, 106].
     */

    int findDerangement(int n) {
        long dn2 = 0, dn1 = 1;
        long res = (n == 1) ? 0 : 1;
        for (int i = 3; i <= n; ++i) {            res = ((i - 1) * (dn1 + dn2)) % 1000000007;            dn2 = dn1;            dn1 = res;                   }

        return (int) res;
    }

        public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
