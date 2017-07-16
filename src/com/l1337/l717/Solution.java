package com.l1337.l717;


public class Solution {

//    https://leetcode.com/problems/1-bit-and-2-bit-characters/discuss/108969/Java-solution-1-or-2
//    https://leetcode.com/problems/1-bit-and-2-bit-characters/discuss/108967/JAVA-check-only-the-end-of-array
//    https://leetcode.com/problems/1-bit-and-2-bit-characters/discuss/152122/Easy-Java-count-1-from-the-end
    public boolean isOneBitCharacter(int[] bits) {
        int length = bits.length;
        if (length == 1)
            return true;
        //length >= 2
        if (bits[length-2] == 0)
            return true;

        //figure out whether sequence from 0,1,2,...l-3 is a valid sequence, if not, then return true. otherwise return false.
        int l = length - 2;
        if (l > 0) {
            boolean ones [] = new boolean[1+l];
            boolean twos [] = new boolean[1+l];

            ones[0] = true;
            twos[0] = true;
            twos[1] = false;
            ones[1] = bits[0] == 0;

            for (int i = 1; i < l; ++i) {
                //bits[i], fill ones[i+1], twos[i+1]
                ones[i+1] = (bits[i] == 0) && (ones[i] || twos[i]);
                twos[i+1] = (bits[i-1] == 1) && (ones[i-1] || twos[i-1]);
            }

            return !(ones[l] || twos[l]);
        } else {
            return false;
        }
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.isOneBitCharacter(new int [] {1, 0, 0}));
    }
}
