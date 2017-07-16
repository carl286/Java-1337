package com.l1337.l458;


public class Solution {

//    http://blog.csdn.net/bone_ace/article/details/44802417
//    https://leetcode.com/problems/poor-pigs/discuss/94266/Another-explanation-and-solution
//    https://leetcode.com/problems/poor-pigs/discuss/94273/Solution-with-detailed-explanation
//    https://leetcode.com/problems/poor-pigs/discuss/94292/major-flaw-in-current-algorithm-fixed
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        return (int)(Math.ceil((Math.log(buckets)/Math.log(minutesToTest / minutesToDie + 1))));
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
